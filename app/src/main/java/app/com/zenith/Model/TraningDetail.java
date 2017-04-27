package app.com.zenith.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class TraningDetail
{
    // TODO Create ***** Rujul *********
    @Override
    public String toString() {
        return "TraningDetail{" +
                "trainingId='" + trainingId + '\'' +
                ", title='" + title + '\'' +
                ", subtitle='" + subtitle + '\'' +
                ", subject='" + subject + '\'' +
                ", image='" + image + '\'' +
                ", video='" + video + '\'' +
                ", content='" + content + '\'' +
                '}';
    }

    @SerializedName("training_id")
@Expose
private String trainingId;
@SerializedName("title")
@Expose
private String title;
@SerializedName("subtitle")
@Expose
private String subtitle;
@SerializedName("subject")
@Expose
private String subject;
@SerializedName("image")
@Expose
private String image;
@SerializedName("video")
@Expose
private String video;
@SerializedName("content")
@Expose
private String content;

public String getTrainingId() {
return trainingId;
}

public void setTrainingId(String trainingId) {
this.trainingId = trainingId;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public String getSubtitle() {
return subtitle;
}

public void setSubtitle(String subtitle) {
this.subtitle = subtitle;
}

public String getSubject() {
return subject;
}

public void setSubject(String subject) {
this.subject = subject;
}

public String getImage() {
return image;
}

public void setImage(String image) {
this.image = image;
}

public String getVideo() {
return video;
}

public void setVideo(String video) {
this.video = video;
}

public String getContent() {
return content;
}

public void setContent(String content) {
this.content = content;
}

}