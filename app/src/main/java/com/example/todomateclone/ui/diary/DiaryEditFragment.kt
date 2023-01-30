package com.example.todomateclone.ui.diary

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.todomateclone.R
import com.example.todomateclone.databinding.FragmentDiaryDetailBinding
import com.example.todomateclone.databinding.FragmentDiaryEditBinding
import com.example.todomateclone.viewmodel.DiaryViewModel
import kotlinx.coroutines.launch
import org.koin.androidx.viewmodel.ext.android.viewModel


class DiaryEditFragment : Fragment() {

    private var _binding: FragmentDiaryEditBinding? = null
    private val binding get() = _binding!!
    private val navigationArgs: DiaryDetailFragmentArgs by navArgs()
    private val diaryViewModel: DiaryViewModel by viewModel()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDiaryEditBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val upButton = binding.upButton
        val saveButton = binding.saveButton
        val diaryTitle = binding.diaryTitle
        val diaryContent = binding.diaryContent


        lifecycleScope.launch {
            diaryViewModel.getIdDiary(navigationArgs.diaryId)
            diaryViewModel.diary.collect {
                diaryTitle.setText(it?.title)
                diaryContent.setText(it?.context)
            }
        }

        upButton.setOnClickListener {
            this.findNavController().navigateUp()
        }

        saveButton.setOnClickListener {
            lifecycleScope.launch {
                diaryViewModel.updateIdDiary(
                    diaryTitle.text.toString(),
                    diaryContent.text.toString(),
                    navigationArgs.diaryId
                )
                findNavController().navigateUp()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        // Hide keyboard.
        val inputMethodManager = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as
                InputMethodManager
        inputMethodManager.hideSoftInputFromWindow(requireActivity().currentFocus?.windowToken, 0)
        _binding = null
    }
}