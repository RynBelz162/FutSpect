package com.belzsoftware.futspect.ui.fixtures

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.belzsoftware.futspect.data.fixtures.FixturesRepository
import com.belzsoftware.futspect.model.fixture.FixtureSearch
import com.belzsoftware.futspect.model.shared.ApiCall
import com.belzsoftware.futspect.model.shared.Result
import javax.inject.Inject

class FixturesViewModel @Inject constructor(
    fixturesRepository: FixturesRepository
) : ViewModel() {

    val fixtures: LiveData<Result<ApiCall<FixtureSearch>>> = fixturesRepository.getFixtures(524)

}