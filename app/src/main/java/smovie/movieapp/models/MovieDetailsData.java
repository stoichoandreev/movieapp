package smovie.movieapp.models;

import android.databinding.BaseObservable;
import android.databinding.Bindable;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import smovie.movieapp.BR;
import smovie.movieapp.R;
import smovie.movieapp.api.pojos.ExtendedMoveData;

/**
 * Created by sniper on 12/7/16.
 * This Data class is example how API data model can be wrapped to View Data model with observable,
 * so every time when data model change the view will be updated
 */

public class MovieDetailsData extends BaseObservable{

    private static final String DEFAULT_VALUE = "...";

    private String title;
    private String year;
    private String rated;
    private String released;
    private String runtime;
    private String genre;
    private String director;
    private String writer;
    private String actors;
    private String plot;
    private String language;
    private String country;
    private String awards;
    private String poster;
    private String metascore;
    private String imdbRating;
    private String imdbVotes;
    private String imdbID;
    private String type;
    private String response;

    private MovieDetailsData(Builder builder) {
        title = builder.title != null ? builder.title : DEFAULT_VALUE;
        year = builder.year != null ? builder.year : DEFAULT_VALUE;
        rated = builder.rated != null ? builder.rated : DEFAULT_VALUE;
        released = builder.released != null ? builder.released : DEFAULT_VALUE;
        runtime = builder.runtime != null ? builder.runtime : DEFAULT_VALUE;
        genre = builder.genre != null ? builder.genre : DEFAULT_VALUE;
        director = builder.director != null ? builder.director : DEFAULT_VALUE;
        writer = builder.writer != null ? builder.writer : DEFAULT_VALUE;
        actors = builder.actors != null ? builder.actors : DEFAULT_VALUE;
        plot = builder.plot != null ? builder.plot : DEFAULT_VALUE;
        language = builder.language != null ? builder.language : DEFAULT_VALUE;
        country = builder.country != null ? builder.country : DEFAULT_VALUE;
        awards = builder.awards != null ? builder.awards : DEFAULT_VALUE;
        poster = builder.poster != null ? builder.poster : DEFAULT_VALUE;
        metascore = builder.metascore != null ? builder.metascore : DEFAULT_VALUE;
        imdbRating = builder.imdbRating != null ? builder.imdbRating : DEFAULT_VALUE;
        imdbVotes = builder.imdbVotes != null ? builder.imdbVotes : DEFAULT_VALUE;
        imdbID = builder.imdbID != null ? builder.imdbID : DEFAULT_VALUE;
        type = builder.type != null ? builder.type : DEFAULT_VALUE;
        response = builder.response != null ? builder.response : "FALSE";
    }

    public static MovieDetailsData from(ExtendedMoveData parseObject) {
        if (parseObject == null) return null;
        return new Builder()
                .title(parseObject.title)
                .year(parseObject.year)
                .rated(parseObject.rated)
                .released(parseObject.released)
                .runtime(parseObject.runtime)
                .genre(parseObject.genre)
                .director(parseObject.director)
                .writer(parseObject.writer)
                .actors(parseObject.actors)
                .plot(parseObject.plot)
                .language(parseObject.language)
                .country(parseObject.country)
                .awards(parseObject.awards)
                .poster(parseObject.poster)
                .metascore(parseObject.metascore)
                .imdbRating(parseObject.imdbRating)
                .imdbVotes(parseObject.imdbVotes)
                .imdbID(parseObject.imdbID)
                .type(parseObject.type)
                .response(parseObject.response)
                .build();
    }

    public static final class Builder {
        private String title;
        private String year;
        private String rated;
        private String released;
        private String runtime;
        private String genre;
        private String director;
        private String writer;
        private String actors;
        private String plot;
        private String language;
        private String country;
        private String awards;
        private String poster;
        private String metascore;
        private String imdbRating;
        private String imdbVotes;
        private String imdbID;
        private String type;
        private String response;

        public Builder() {}

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder year(String val) {
            year = val;
            return this;
        }

        public Builder rated(String val) {
            rated = val;
            return this;
        }
        public Builder released(String val) {
            released = val;
            return this;
        }
        public Builder runtime(String val) {
            runtime = val;
            return this;
        }
        public Builder genre(String val) {
            genre = val;
            return this;
        }
        public Builder director(String val) {
            director = val;
            return this;
        }
        public Builder writer(String val) {
            writer = val;
            return this;
        }
        public Builder actors(String val) {
            actors = val;
            return this;
        }
        public Builder plot(String val) {
            plot = val;
            return this;
        }
        public Builder language(String val) {
            language = val;
            return this;
        }
        public Builder country(String val) {
            country = val;
            return this;
        }
        public Builder awards(String val) {
            awards = val;
            return this;
        }
        public Builder poster(String val) {
            poster = val;
            return this;
        }
        public Builder metascore(String val) {
            metascore = val;
            return this;
        }
        public Builder imdbRating(String val) {
            imdbRating = val;
            return this;
        }
        public Builder imdbVotes(String val) {
            imdbVotes = val;
            return this;
        }
        public Builder imdbID(String val) {
            imdbID = val;
            return this;
        }
        public Builder type(String val) {
            type = val;
            return this;
        }
        public Builder response(String val) {
            response = val;
            return this;
        }


        public MovieDetailsData build() {
            return new MovieDetailsData(this);
        }
    }

    @Bindable
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
        notifyPropertyChanged(BR.title);
    }
    @Bindable
    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
        notifyPropertyChanged(BR.year);
    }
    @Bindable
    public String getRated() {
        return rated;
    }

    public void setRated(String rated) {
        this.rated = rated;
        notifyPropertyChanged(BR.rated);
    }
    @Bindable
    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
        notifyPropertyChanged(BR.released);
    }
    @Bindable
    public String getRuntime() {
        return runtime;
    }

    public void setRuntime(String runtime) {
        this.runtime = runtime;
        notifyPropertyChanged(BR.runtime);
    }
    @Bindable
    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
        notifyPropertyChanged(BR.genre);
    }
    @Bindable
    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
        notifyPropertyChanged(BR.director);
    }
    @Bindable
    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
        notifyPropertyChanged(BR.writer);
    }
    @Bindable
    public String getActors() {
        return actors;
    }

    public void setActors(String actors) {
        this.actors = actors;
        notifyPropertyChanged(BR.actors);
    }
    @Bindable
    public String getPlot() {
        return plot;
    }

    public void setPlot(String plot) {
        this.plot = plot;
        notifyPropertyChanged(BR.plot);
    }
    @Bindable
    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
        notifyPropertyChanged(BR.language);
    }
    @Bindable
    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
        notifyPropertyChanged(BR.country);
    }
    @Bindable
    public String getAwards() {
        return awards;
    }

    public void setAwards(String awards) {
        this.awards = awards;
        notifyPropertyChanged(BR.awards);
    }
    @Bindable
    public String getPoster() {
        return poster;
    }

    public void setPoster(String poster) {
        this.poster = poster;
        notifyPropertyChanged(BR.poster);
    }
    @Bindable
    public String getMetascore() {
        return metascore;
    }

    public void setMetascore(String metascore) {
        this.metascore = metascore;
        notifyPropertyChanged(BR.metascore);
    }
    @Bindable
    public String getImdbRating() {
        return imdbRating;
    }

    public void setImdbRating(String imdbRating) {
        this.imdbRating = imdbRating;
        notifyPropertyChanged(BR.imdbRating);
    }
    @Bindable
    public String getImdbVotes() {
        return imdbVotes;
    }

    public void setImdbVotes(String imdbVotes) {
        this.imdbVotes = imdbVotes;
        notifyPropertyChanged(BR.imdbVotes);
    }
    @Bindable
    public String getImdbID() {
        return imdbID;
    }

    public void setImdbID(String imdbID) {
        this.imdbID = imdbID;
        notifyPropertyChanged(BR.imdbID);
    }
    @Bindable
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
        notifyPropertyChanged(BR.type);
    }
    @Bindable
    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
        notifyPropertyChanged(BR.response);
    }
    //Annotated Binding adapter method must be static
    @BindingAdapter({"bind:poster"})
    public static void loadImage(ImageView view, String imageUrl) {
        Picasso.with(view.getContext())
                .load(imageUrl)
                .placeholder(R.drawable.placeholder)
                .into(view);
    }
}
