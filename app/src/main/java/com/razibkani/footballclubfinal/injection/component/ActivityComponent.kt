package com.razibkani.footballclubfinal.injection.component

import com.razibkani.footballclubfinal.injection.PerActivity
import com.razibkani.footballclubfinal.injection.module.ActivityModule
import com.razibkani.footballclubfinal.ui.detailmatch.MatchDetailActivity
import com.razibkani.footballclubfinal.ui.detailteam.DetailTeamActivity
import com.razibkani.footballclubfinal.ui.detailteam.teamplayer.TeamPlayerFragment
import com.razibkani.footballclubfinal.ui.favorites.favoritesmatch.FavoritesMatchFragment
import com.razibkani.footballclubfinal.ui.favorites.favoritesteam.FavoritesTeamFragment
import com.razibkani.footballclubfinal.ui.matches.nextmatch.NextMatchListFragment
import com.razibkani.footballclubfinal.ui.matches.prevmatch.PrevMatchListFragment
import com.razibkani.footballclubfinal.ui.teams.TeamsFragment
import dagger.Component

@PerActivity
@Component(dependencies = [ApplicationComponent::class], modules = [ActivityModule::class])
interface ActivityComponent {

    fun inject(matchDetailActivity: MatchDetailActivity)
    fun inject(detailTeamActivity: DetailTeamActivity)

    fun inject(prevMatchListFragment: PrevMatchListFragment)
    fun inject(nextMatchListFragment: NextMatchListFragment)
    fun inject(favoritesMatchFragment: FavoritesMatchFragment)
    fun inject(favoritesTeamFragment: FavoritesTeamFragment)
    fun inject(teamsFragment: TeamsFragment)
    fun inject(teamPlayerFragment: TeamPlayerFragment)

}