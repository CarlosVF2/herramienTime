/*
 * Copyright 2016 Jorge Grau Llopis
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package android.com.herramientime.core.presenter;

public interface MvpPresenter {

    /**
     * Método para notificar al Presenter que la vista está cargada. Se debe utilizar
     * para empezar el ciclo de vida del Presenter e iniciar los procesos del mismo.
     */
    void onViewBinded();

    /**
     * Método para notificar que la Vista deja de estar disponible.
     */
    void onViewUnbinded();

    /**
     * Método para limpiar dependencias al destruir el presenter.
     */
    void onDestroy();
}
