package com.waffle22.wafflytime.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.waffle22.wafflytime.databinding.FragmentForumBinding

class ForumFragment : Fragment() {
    private lateinit var binding: FragmentForumBinding

    private val viewModel: ForumViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentForumBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val forumPreviewAdapter = ForumPreviewAdapter()
        viewModel.threads.observe(this.viewLifecycleOwner){ items ->
            items.let{
                forumPreviewAdapter.submitList(it)
            }
        }
        binding.threads.adapter = forumPreviewAdapter
        binding.threads.layoutManager = LinearLayoutManager(this.context)

        val forumAnnouncementAdapter = ForumAnnouncementAdapter()
        viewModel.announcements.observe(this.viewLifecycleOwner){ items->
            items.let{
                forumAnnouncementAdapter.submitList(it)
            }
        }
        binding.announcements.adapter = forumAnnouncementAdapter
        binding.announcements.layoutManager = LinearLayoutManager(this.context)
    }
}