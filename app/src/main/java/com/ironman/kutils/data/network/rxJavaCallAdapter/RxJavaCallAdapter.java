/*
 * Copyright (C) 2016 Jake Wharton
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ironman.kutils.data.network.rxJavaCallAdapter;

import java.lang.reflect.Type;

import retrofit2.Call;
import retrofit2.CallAdapter;
import retrofit2.Response;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Scheduler;

final class RxJavaCallAdapter implements CallAdapter<Object> {
  private final Type responseType;
  private final Scheduler scheduler;
  private final boolean isResult;
  private final boolean isBody;
  private final boolean isSingle;
  private final boolean isCompletable;

  RxJavaCallAdapter(Type responseType, Scheduler scheduler, boolean isResult, boolean isBody,
                    boolean isSingle, boolean isCompletable) {
    this.responseType = responseType;
    this.scheduler = scheduler;
    this.isResult = isResult;
    this.isBody = isBody;
    this.isSingle = isSingle;
    this.isCompletable = isCompletable;
  }

  @Override
  public Type responseType() {
    return responseType;
  }

  @Override
  public <R> Object adapt(Call<R> call) {
    OnSubscribe<Response<R>> callFunc = new CallOnSubscribe<>(call);

    OnSubscribe<?> func;
    if (isResult) {
      func = new ResultOnSubscribe<>(callFunc);
    } else if (isBody) {
      func = new BodyOnSubscribe<>(callFunc);
    } else {
      func = callFunc;
    }
    Observable<?> observable = Observable.create(func);

    if (scheduler != null) {
      observable = observable.subscribeOn(scheduler);
    }

    if (isSingle) {
      return SingleHelper.toSingle(observable);
    }
    if (isCompletable) {
      return CompletableHelper.toCompletable(observable);
    }
    return observable;
  }

  /**
   * Separate static class defers classloading and bytecode verification since Completable is not an
   * RxJava stable API yet.
   */
  private static final class CompletableHelper {
    static Object toCompletable(Observable<?> observable) {
      return observable.toCompletable();
    }
  }

  /**
   * Separate static class defers classloading and bytecode verification since Single is not an
   * RxJava stable API yet.
   */
  private static final class SingleHelper {
    static Object toSingle(Observable<?> observable) {
      return observable.toSingle();
    }
  }
}
