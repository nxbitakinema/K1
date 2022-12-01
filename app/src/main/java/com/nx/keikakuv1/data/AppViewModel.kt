package com.nx.keikakuv1.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AppViewModel (private val db: Daos) : ViewModel() {

    val nxrs: LiveData<List<Nx>> = db.getDAOs()


    fun deleteAppViewModel(nxr: Nx) {
        viewModelScope.launch(Dispatchers.IO){
            db.deleteDAOs(nxr)
        }
    }

    fun updateAppViewModel(nxr: Nx) {
        viewModelScope.launch(Dispatchers.IO){
            db.updateDAOs(nxr)
        }
    }

    fun createAppViewModel(title: String, nxr: String, image: String? = null) {
        viewModelScope.launch(Dispatchers.IO){
            db.insertDAOs( Nx (
                title = title,
                text = nxr,
                imageUri = image )
            )
        }
    }

    suspend fun getAppViewModel(nxrId : Int) : Nx? {
        return db.getDAOsById(nxrId)
    }


}

class ViewModelFactory(
    private val db: Daos,
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return  AppViewModel(
            db = db,
        ) as T
    }

}