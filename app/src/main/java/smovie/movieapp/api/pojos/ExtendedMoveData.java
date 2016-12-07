package smovie.movieapp.api.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by sniper on 12/6/16.
 */

public class ExtendedMoveData {
    @SerializedName("Title")
    @Expose
    public String title;
    @SerializedName("Year")
    @Expose
    public String year;
    @SerializedName("Rated")
    @Expose
    public String rated;
    @SerializedName("Released")
    @Expose
    public String released;
    @SerializedName("Runtime")
    @Expose
    public String runtime;
    @SerializedName("Genre")
    @Expose
    public String genre;
    @SerializedName("Director")
    @Expose
    public String director;
    @SerializedName("Writer")
    @Expose
    public String writer;
    @SerializedName("Actors")
    @Expose
    public String actors;
    @SerializedName("Plot")
    @Expose
    public String plot;
    @SerializedName("Language")
    @Expose
    public String language;
    @SerializedName("Country")
    @Expose
    public String country;
    @SerializedName("Awards")
    @Expose
    public String awards;
    @SerializedName("Poster")
    @Expose
    public String poster;
    @SerializedName("Metascore")
    @Expose
    public String metascore;
    @SerializedName("imdbRating")
    @Expose
    public String imdbRating;
    @SerializedName("imdbVotes")
    @Expose
    public String imdbVotes;
    @SerializedName("imdbID")
    @Expose
    public String imdbID;
    @SerializedName("Type")
    @Expose
    public String type;
    @SerializedName("Response")
    @Expose
    public String response;
}
