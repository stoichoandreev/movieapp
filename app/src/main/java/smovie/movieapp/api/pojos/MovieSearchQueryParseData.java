package smovie.movieapp.api.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sniper on 12/6/16.
 */

public class MovieSearchQueryParseData {
    @SerializedName("Search")
    @Expose
    private List<MovieData> movieList;
    @SerializedName("totalResults")
    @Expose
    private String totalResults;
    @SerializedName("Response")
    @Expose
    private String response;
    @SerializedName("Error")
    @Expose
    private String error;

    /**
     *
     * @return
     * The movieList
     */
    public List<MovieData> getMovieList() {
        return movieList;
    }

    /**
     *
     * @param movieList
     * The Search
     */
    public void setMovieList(List<MovieData> movieList) {
        this.movieList = movieList;
    }

    /**
     *
     * @return
     * The totalResults
     */
    public String getTotalResults() {
        return totalResults;
    }

    /**
     *
     * @param totalResults
     * The totalResults
     */
    public void setTotalResults(String totalResults) {
        this.totalResults = totalResults;
    }

    /**
     *
     * @return
     * The response
     */
    public String getResponse() {
        return response;
    }

    /**
     *
     * @param response
     * The Response
     */
    public void setResponse(String response) {
        this.response = response;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
