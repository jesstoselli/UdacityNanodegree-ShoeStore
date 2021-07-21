package com.udacity.shoestore.ui

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.withStyledAttributes
import com.udacity.shoestore.R
import kotlinx.android.synthetic.main.shoe_list_item.view.*

class ShoeItem: ConstraintLayout {

    private lateinit var textViewShoeCompany: TextView
    private lateinit var textViewShoeName: TextView
    private lateinit var textViewShoeSize: TextView
    private lateinit var textViewShoeDescription: TextView

    constructor(context: Context) : super(context) {
        setup(context, null)
    }

    constructor(context: Context, attrs: AttributeSet) : super(context, attrs) {
        setup(context, attrs)
    }

    constructor(ctx: Context, attrs: AttributeSet?, def: Int) : super(ctx, attrs, def) {
        setup(context, attrs)
    }

    fun setShoeCompany(label: String) {
        textViewShoeCompany.text = label
    }

    fun setShoeName(value: String) {
        textViewShoeName.text = value
    }

    fun setShoeSize(value: String) {
        textViewShoeSize.text = value
    }

    fun setShoeDescription(value: String) {
        textViewShoeDescription.text = value
    }

    private fun setup(context: Context, attrs: AttributeSet?) {
        LayoutInflater.from(context)
            .inflate(R.layout.shoe_list_item, this, true)

        textViewShoeCompany = shoeCompany_textView
        textViewShoeName = shoeName_textView
        textViewShoeSize = shoeSizeValue_textView
        textViewShoeDescription = shoeDescription_textView

        context.withStyledAttributes(attrs, R.styleable.ShoeItem) {
            setShoeCompany(getString(R.styleable.ShoeItem_company).orEmpty())
            setShoeName(getString(R.styleable.ShoeItem_name).orEmpty())
            setShoeSize(getString(R.styleable.ShoeItem_shoeSize).orEmpty())
            setShoeDescription(getString(R.styleable.ShoeItem_description).orEmpty())
        }
    }
}
