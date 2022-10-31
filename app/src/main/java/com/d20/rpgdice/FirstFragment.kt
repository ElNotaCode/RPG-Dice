package com.d20.rpgdice

import android.media.MediaPlayer
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.d20.rpgdice.databinding.FragmentFirstBinding

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null
    private lateinit var rollDiceMediaPlayer: MediaPlayer
    private lateinit var criticalMediaPlayer: MediaPlayer
    private lateinit var failMediaPlayer: MediaPlayer

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        rollDiceMediaPlayer = MediaPlayer.create(context, R.raw.d20roll)
        criticalMediaPlayer = MediaPlayer.create(context, R.raw.critical1)
        failMediaPlayer = MediaPlayer.create(context, R.raw.fail1)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ibDice.setOnClickListener {
            //TIRADA
            //1.Se saca el resultado Int
            val diceResult = getRandom(20)
            //2.Hace el sonido de dado
            //TODO: Poner de manera correcta
            when(diceResult){
                20 ->{
                    if(RpgDiceApplication.prefs.getCriticalSoundInd()) {
                        startSound(criticalMediaPlayer)
                    }else if(RpgDiceApplication.prefs.getDiceSoundInd()) {
                        startSound(rollDiceMediaPlayer)
                    }
                }
                1 ->{
                    if(RpgDiceApplication.prefs.getFailSoundInd()) {
                        startSound(failMediaPlayer)
                    }else if(RpgDiceApplication.prefs.getDiceSoundInd()) {
                        startSound(rollDiceMediaPlayer)
                    }
                }
                else -> {
                    if(RpgDiceApplication.prefs.getDiceSoundInd()) {
                        startSound(rollDiceMediaPlayer)
                    }
                }
            }
            //3.Se muestra por pantalla
            view?.findViewById<TextView>(R.id.tv_resultado)?.text = diceResult.toString()
            //4.Se muestra la frase dependiendo del resultado
            view?.findViewById<TextView>(R.id.tv_sentence)?.text = getSentence(diceResult)
            //5.Se reproduce el sonido dependiendo del resultado

        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun getRandom(max :Int): Int{
        val random = java.util.Random()
        return random.nextInt(max) + 1
    }

    private fun getRandomPosition(max :Int): Int{
        val random = java.util.Random()
        return random.nextInt(max)
    }

    private fun startSound(mediaPlayer: MediaPlayer?) {
        mediaPlayer?.start()
    }

    private fun getSentence(diceResult: Int): String{
        if(diceResult == 20){
            val rand = getRandomPosition(resources.getStringArray(R.array.on_critical_array).size)
            return resources.getStringArray(R.array.on_critical_array)[rand]
        }

        if (diceResult == 1){
            val rand = getRandomPosition(resources.getStringArray(R.array.on_fail_array).size)
            return resources.getStringArray(R.array.on_fail_array)[rand]
        }

        val rand = getRandomPosition(resources.getStringArray(R.array.on_neutral_array).size)
        return resources.getStringArray(R.array.on_neutral_array)[rand]
    }

}
