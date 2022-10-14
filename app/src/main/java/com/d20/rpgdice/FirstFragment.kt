package com.d20.rpgdice

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
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
    private lateinit var appContext: Context

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.ibDice.setOnClickListener {
            var mediaPlayer: MediaPlayer? = MediaPlayer.create(context, R.raw.d20roll)
            mediaPlayer?.start()
            rollDice()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun rollDice(){
        val random = java.util.Random()
        val randomNumber = random.nextInt(20) + 1
        view?.findViewById<TextView>(R.id.tv_resultado)?.text = randomNumber.toString()
    }

    // extension function to get raw resource uri by resource name
    private fun Context.rawResourceUriByName(fileName:String):Uri{
        // get resource uri from res/raw folder by file name
        return Uri.parse("android.resource://$packageName/raw/$fileName")
    }

}
