package smovie.movieapp.api.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by sniper on 12/6/16.
 */

public class MovieData implements Serializable{
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("Year")
    @Expose
    private String year;
    @SerializedName("imdbID")
    @Expose
    private String imdbID;
    @SerializedName("Type")
    @Expose
    private String type;
    @SerializedName("Poster")
    @Expose
    private String poster;

    /**
     *
     * @return
     * The title
     */
    public String getTitle() {
        return title;
    }

    /**
     *
     * @param title
     * The Title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     *
     * @return
     * The year
     */
    public String getYear() {
        return year;
    }

    /**
     *
     * @param year
     * The Year
     */
    public void setYear(String year) {
        this.year = year;
    }

    /**
     *
     * @return
     * The imdbID
     */
    public String getImdbID() {
        return imdbID;
    }

    /**
     *
     * @param imdbID
     * The imdbID
     */
    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
    }

    /**
     *
     * @return
     * The type
     */
    public String getType() {
        return type;
    }

    /**
     *
     * @param type
     * The Type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return
     * The poster
     */
    public String getPoster() {
        return poster;
    }

    /**
     *
     * @param poster
     * The Poster
     */
    public void setPoster(String poster) {
        this.poster = poster;
    }

}
