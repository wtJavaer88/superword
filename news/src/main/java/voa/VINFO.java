package voa;

public class VINFO
{
    private int time;
    private String en;
    private String ch;

    public VINFO()
    {

    }

    public int getTime()
    {
        return time;
    }

    public void setTime(int time)
    {
        this.time = time;
    }

    public String getEn()
    {
        return en;
    }

    public void setEn(String en)
    {
        this.en = en;
    }

    public String getCh()
    {
        return ch;
    }

    public void setCh(String ch)
    {
        this.ch = ch;
    }

    @Override
    public String toString()
    {
        return "VINFO [time=" + time + ", en=" + en + ", ch=" + ch + "]";
    }
}