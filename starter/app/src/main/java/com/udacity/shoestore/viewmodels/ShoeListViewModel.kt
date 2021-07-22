package com.udacity.shoestore.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.udacity.shoestore.models.Shoe
import timber.log.Timber

class ShoeListViewModel : ViewModel() {

    private val _shoe = MutableLiveData<Shoe>()
    val shoe: LiveData<Shoe>
        get() = _shoe

    private val _shoeList = MutableLiveData<MutableList<Shoe>>()
    val shoeList: LiveData<MutableList<Shoe>>
        get() = _shoeList

    private val _hasShoeBeenAdded = MutableLiveData<Boolean>()
    val hasShoeBeenAdded: LiveData<Boolean>
        get() = _hasShoeBeenAdded

    private val _isUserLoggedIn = MutableLiveData<Boolean>()
    val isUserLoggedIn: LiveData<Boolean>
        get() = _isUserLoggedIn


    init {
        _shoe.value = Shoe("", 0.0, "", "", mutableListOf())
        _shoeList.value = originalShoeList()
        _hasShoeBeenAdded.value = false
        _isUserLoggedIn.value = true
    }

    private fun originalShoeList(): MutableList<Shoe> {
        return mutableListOf(
            Shoe(
                "Authentic",
                5.5,
                "Vans",
                "A low top shoe with a shorter tongue than the Old Skool, with sturdy padding, secure laces and our signature waffle outsoles."
            ),
            Shoe(
                "Run Star Hike",
                7.0,
                "Converse",
                "This Run Star Hike is a reimagination of the iconic Converse Chuck Taylor All Star with enhanced on-court control. While a contrast-color star on the heel defines the modern platform style, the lace-up canvas keeps it classic."
            ),
            Shoe(
                "Nike",
                6.5,
                "Air Max 97",
                "Originally released in 1997, the Air Max 97 was the first Nike shoe to feature a full-length visible Air unit from heel to toe. Renowned for its bold wavy design across the upper, reflective accents, minimal branding, and large Air bubble."
            ),
            Shoe(
                "Adidas",
                7.5,
                "Ultraboost 21",
                "Pick up the pace comfortably with adidas Women's Ultraboost 21 Running Shoes. With Boost cushioning that returns energy to every stride you make, put on more miles by throwing on a pair of these shoes."
            ),
            Shoe(
                "Crocs",
                8.0,
                "Classic Platform",
                "Flexible, lightweight, and undeniably comfortable, these crocs featuring classic clog upper add a new style dimension to your closet."
            )
        )
        // _shoeList.postValue(originalShoeList)
    }

    fun addShoeToList(): Boolean {
        if (validateFields()) {
            _shoeList.value?.add(_shoe.value!!)
            _hasShoeBeenAdded.value = true
            return true
        } else return false
    }

    private fun validateFields(): Boolean {
        return shoe.value?.name?.isNotEmpty() == true && shoe.value?.company?.isNotEmpty() == true && shoe.value?.size.toString()
            .isNotEmpty() && shoe.value?.description?.isNotEmpty() == true
    }

    fun onAddNewShoeToList() {
        _hasShoeBeenAdded.value = false
    }

    fun onLogOut() {
        _isUserLoggedIn.value = false
    }

    fun resetForm() {
        _shoe.value = Shoe("", 0.0, "", "")
    }

}