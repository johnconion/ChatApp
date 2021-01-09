package tokyo.crouton.datasource_realm

import io.reactivex.Observable
import io.realm.Realm
import io.realm.RealmObject

class RealmManager {
    companion object {
        fun <T : RealmObject> createObservable(type: Class<T>): Observable<List<T>> =
            Observable.create<List<T>> { emitter ->
                Realm.getDefaultInstance().where(type)
                    .findAllAsync()
                    .addChangeListener { values ->
                        emitter.onNext(values)
                    }
            }
    }
}