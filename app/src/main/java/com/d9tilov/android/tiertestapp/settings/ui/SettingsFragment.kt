package com.d9tilov.android.tiertestapp.settings.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.FrameLayout
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import com.d9tilov.android.tiertestapp.R
import com.d9tilov.android.tiertestapp.vehicle.data.entity.VehicleModel
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SettingsFragment : BottomSheetDialogFragment(), CompoundButton.OnCheckedChangeListener {

    private val viewModel by viewModels<SettingsViewModel>()
    private var areSettingsCreated = false

    private val parentLayout: LinearLayout by lazy { requireView().findViewById(R.id.settings_layout) }
    private val seekBarLayout: ConstraintLayout by lazy { requireView().findViewById(R.id.settings_charge_level_spinner_layout) }
    private val seekBar: SeekBar by lazy { seekBarLayout.findViewById(R.id.settings_charge_level_spinner) }
    private val seekBarProgress: TextView by lazy { seekBarLayout.findViewById(R.id.settings_charge_level_spinner_value) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.settings_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.models().observe(this.viewLifecycleOwner, {
            if (areSettingsCreated) {
                return@observe
            }
            it.forEachIndexed { index, vehicleModel ->
                val modelCheckBox = CheckBox(requireContext())
                modelCheckBox.text = vehicleModel.name
                modelCheckBox.tag = vehicleModel
                val params = parentLayout.layoutParams as FrameLayout.LayoutParams
                when (index == it.size - 1) {
                    true -> params.setMargins(24, 8, 0, 16)
                    false -> params.setMargins(24, 8, 0, 0)
                }
                modelCheckBox.layoutParams = params
                modelCheckBox.setOnCheckedChangeListener(this)
                parentLayout.addView(modelCheckBox)
            }
            areSettingsCreated = true
        })
        viewModel.settings().observe(this.viewLifecycleOwner, {
            seekBar.progress = it.chargeLevel
            seekBarProgress.text =
                getString(R.string.settings_progress_value, it.chargeLevel.toString())
            for (model in it.models) {
                val checkBox: CheckBox? = parentLayout.findViewWithTag(model)
                checkBox?.isChecked = true
            }

        })
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) {
                seekBarProgress.text =
                    getString(R.string.settings_progress_value, progress.toString())
            }

            override fun onStartTrackingTouch(seekBar: SeekBar) {
            }

            override fun onStopTrackingTouch(seekBar: SeekBar) {
                viewModel.updateChargeLevel(seekBar.progress)
            }
        })
    }

    override fun onCheckedChanged(buttonView: CompoundButton, isChecked: Boolean) {
        viewModel.updateModel(buttonView.tag as VehicleModel, isChecked)
    }

    companion object {
        fun newInstance() = SettingsFragment()
    }
}