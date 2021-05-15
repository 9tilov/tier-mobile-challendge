package com.d9tilov.android.tiertestapp.map.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.d9tilov.android.tiertestapp.R
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class BookVehicleFragment : BottomSheetDialogFragment() {

    private val viewModel by viewModels<BookVehicleViewModel>()
    private val icon: ImageView by lazy { requireView().findViewById(R.id.booking_model_image) }
    private val applyBtn: TextView by lazy { requireView().findViewById(R.id.booking_apply) }
    private val modelTitle: TextView by lazy { requireView().findViewById(R.id.booking_model_value) }
    private val chargeLevel: TextView by lazy { requireView().findViewById(R.id.booking_model_charge_level_value) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.getLong(EXTRA_VEHICLE_ID)?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.book_vehicle_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        applyBtn.setOnClickListener {
            Toast.makeText(
                requireContext(),
                R.string.booking_message,
                Toast.LENGTH_SHORT
            ).show()
        }
        viewModel.getVehicle().observe(viewLifecycleOwner, {
            modelTitle.text = it.model.name
            chargeLevel.text = getString(R.string.booking_charge_value, it.battery.toString())
            when (it.model.name) {
                "AA" -> icon.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_baseline_directions_car_24
                    )
                )
                "AB" -> icon.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_baseline_electric_bike_24
                    )
                )
                "UNKNOWN" -> icon.setImageDrawable(
                    ContextCompat.getDrawable(
                        requireContext(),
                        R.drawable.ic_baseline_directions_bike_24
                    )
                )
            }
        })
    }

    companion object {

        const val EXTRA_VEHICLE_ID = "vehicle.id"
        fun newInstance(vehicleId: Long) = BookVehicleFragment().apply {
            arguments = Bundle().apply {
                putLong(EXTRA_VEHICLE_ID, vehicleId)
            }
        }
    }
}
