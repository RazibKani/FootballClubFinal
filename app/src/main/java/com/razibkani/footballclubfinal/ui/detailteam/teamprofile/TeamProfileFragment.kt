package com.razibkani.footballclubfinal.ui.detailteam.teamprofile


import android.os.Bundle
import android.support.v4.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.razibkani.footballclubfinal.R
import kotlinx.android.synthetic.main.fragment_team_profile.*

class TeamProfileFragment : Fragment() {

    private var teamDescription: String? = null

    companion object {
        val TAG: String = TeamProfileFragment::class.java.simpleName
        private const val ARG_DESCRIPTION = "ARG_DESCRIPTION"

        fun newInstance(teamDescription: String?) : TeamProfileFragment {
            val fragment = TeamProfileFragment()
            val bundle = Bundle()
            bundle.putString(ARG_DESCRIPTION, teamDescription)

            fragment.arguments = bundle
            return fragment
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        teamDescription = arguments?.getString(ARG_DESCRIPTION)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_team_profile, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textTeamProfile.text = teamDescription
    }
}
