package voa;

import com.wnc.news.api.common.NewsInfo;

public class VoaNewsInfo extends NewsInfo
{
    private String mp3;

    public String getMp3()
    {
        return mp3;
    }

    public void setMp3(String mp3)
    {
        this.mp3 = mp3;
    }

    @Override
    public String toString()
    {
        return "VoaNewsInfo [mp3=" + mp3 + ", website=" + website + ", title="
                + title + ", url=" + url + ", sub_text=" + sub_text + ", date="
                + date + ", head_pic=" + head_pic + ", html_content="
                + html_content + ", db_id=" + db_id + ", cet_topics="
                + cet_topics + ", keywords=" + keywords + ", topic_counts="
                + topic_counts + ", comment_counts=" + comment_counts
                + ", create_time=" + create_time + "]";
    }
}
