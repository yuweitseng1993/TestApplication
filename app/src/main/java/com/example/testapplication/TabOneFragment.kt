package com.example.testapplication

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.internal.VisibilityAwareImageButton
import kotlinx.android.synthetic.main.fragment_one.*

class TabOneFragment : Fragment() {
    private val STATE = "STATE"
    private val PREV_STATE = "PREV_STATE"
    private var state = 0
    private var prevState = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_one, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        btn_back.setOnClickListener {
            onBackButtonPressed()
        }

        btn_continue.setOnClickListener {
            onContinueButtonPressed()
        }
    }

    private fun onBackButtonPressed() {
        prevState = state
        if(state > 0)
            state -= 1
        if(state < 3)
            btn_continue.visibility = View.VISIBLE
        if(state == 0)
            btn_back.visibility = View.INVISIBLE
        updateStates(state, prevState)
    }

    private fun onContinueButtonPressed() {
        prevState = state
        if(state < 3)
            state += 1
        if(state > 0)
            btn_back.visibility = View.VISIBLE
        if(state == 3)
            btn_continue.visibility = View.INVISIBLE
        updateStates(state, prevState)
    }

    private fun updateStates(state: Int, prevState: Int) {
        when(state) {
            0 -> {
                state_0.background = resources.getDrawable(R.drawable.selected_background)
                state_0_text.setTextColor(resources.getColor(R.color.selected_text))
            }
            1-> {
                state_1.background = resources.getDrawable(R.drawable.selected_background)
                state_1_text.setTextColor(resources.getColor(R.color.selected_text))
            }
            2 -> {
                state_2.background = resources.getDrawable(R.drawable.selected_background)
                state_2_text.setTextColor(resources.getColor(R.color.selected_text))
            }
            3 -> {
                state_3.background = resources.getDrawable(R.drawable.selected_background)
                state_3_text.setTextColor(resources.getColor(R.color.selected_text))
            }
        }

        when(prevState) {
            0 -> {
                state_0.background = resources.getDrawable(R.drawable.unselected_background)
                state_0_text.setTextColor(resources.getColor(R.color.unselected_bg))
            }
            1-> {
                state_1.background = resources.getDrawable(R.drawable.unselected_background)
                state_1_text.setTextColor(resources.getColor(R.color.unselected_bg))
            }
            2 -> {
                state_2.background = resources.getDrawable(R.drawable.unselected_background)
                state_2_text.setTextColor(resources.getColor(R.color.unselected_bg))
            }
            3 -> {
                state_3.background = resources.getDrawable(R.drawable.unselected_background)
                state_3_text.setTextColor(resources.getColor(R.color.unselected_bg))
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.run {
            putInt(STATE, state)
            putInt(PREV_STATE, prevState)
        }
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        if(savedInstanceState != null) {
            state = savedInstanceState.getInt(STATE)
            prevState = savedInstanceState.getInt(PREV_STATE)
            updateStates(state, prevState)
        }
    }
}