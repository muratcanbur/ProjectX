package co.icanteach.projectx

import org.junit.runner.RunWith
import org.junit.runners.Suite

@RunWith(Suite::class)
@Suite.SuiteClasses(
    PopularTVShowsViewModelTest::class,
    PopularTVShowsFeedItemViewStateTest::class
)
class PopularTvShowsTestSuite