package com.d20.rpgdice

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.d20.rpgdice.databinding.FragmentSettingsBinding

class SettingsFragment : Fragment() {

    private var _binding: FragmentSettingsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentSettingsBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.cbDiceSound.isChecked = RpgDiceApplication.prefs.getDiceSoundInd()
        binding.cbCriticalSound.isChecked = RpgDiceApplication.prefs.getCriticalSoundInd()
        binding.cbFailsSound.isChecked = RpgDiceApplication.prefs.getFailSoundInd()

        binding.btnSave.setOnClickListener{

            RpgDiceApplication.prefs.setDiceSoundInd(binding.cbDiceSound.isChecked)
            RpgDiceApplication.prefs.setCriticalSoundInd(binding.cbCriticalSound.isChecked)
            RpgDiceApplication.prefs.setFailSoundInd(binding.cbFailsSound.isChecked)

            findNavController().navigate(R.id.action_SettingsFragment_to_FirstFragment)
        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}