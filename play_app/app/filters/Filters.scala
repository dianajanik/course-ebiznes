package filters

/**
  * Created by dianajanik on 14.05.2019
  */
import javax.inject.Inject
import play.api.http.HttpFilters
import play.filters.cors.CORSFilter
class Filters @Inject() (corsFilter: CORSFilter) extends HttpFilters {
  def filters = Seq(corsFilter)
}
