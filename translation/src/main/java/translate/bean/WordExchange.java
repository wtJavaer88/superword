package translate.bean;

public class WordExchange
{
    private String word_third;// 第三人称
    private String word_done;// 过去完成时
    private String word_pl;// 复数
    private String word_ing;// 现在分词
    private String word_past;// 过去式
    private String word_est;//
    private String word_er;//

    public String getWord_third()
    {
        return word_third;
    }

    public void setWord_third(String word_third)
    {
        this.word_third = word_third;
    }

    public String getWord_done()
    {
        return word_done;
    }

    public void setWord_done(String word_done)
    {
        this.word_done = word_done;
    }

    public String getWord_pl()
    {
        return word_pl;
    }

    public void setWord_pl(String word_pl)
    {
        this.word_pl = word_pl;
    }

    public String getWord_est()
    {
        return word_est;
    }

    public void setWord_est(String word_est)
    {
        this.word_est = word_est;
    }

    public String getWord_ing()
    {
        return word_ing;
    }

    public void setWord_ing(String word_ing)
    {
        this.word_ing = word_ing;
    }

    public String getWord_er()
    {
        return word_er;
    }

    public void setWord_er(String word_er)
    {
        this.word_er = word_er;
    }

    public String getWord_past()
    {
        return word_past;
    }

    public void setWord_past(String word_past)
    {
        this.word_past = word_past;
    }

    @Override
    public String toString()
    {
        return "WordExhange [word_third=" + word_third + ", word_done="
                + word_done + ", word_pl=" + word_pl + ", word_est=" + word_est
                + ", word_ing=" + word_ing + ", word_er=" + word_er
                + ", word_past=" + word_past + "]";
    }
}
