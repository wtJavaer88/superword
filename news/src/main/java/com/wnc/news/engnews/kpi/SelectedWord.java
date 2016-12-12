package com.wnc.news.engnews.kpi;

public class SelectedWord
{
	private int topic_id;
	private String word;
	private String cn_mean;

	public SelectedWord()
	{

	}

	public SelectedWord(String word)
	{
		this.word = word;
	}

	public int getTopic_id()
	{
		return topic_id;
	}

	public void setTopic_id(int topic_id)
	{
		this.topic_id = topic_id;
	}

	public String getWord()
	{
		return word;
	}

	public void setWord(String word)
	{
		this.word = word;
	}

	public String getCn_mean()
	{
		return cn_mean;
	}

	public void setCn_mean(String cn_mean)
	{
		this.cn_mean = cn_mean;
	}

	@Override
	public String toString()
	{
		return "SelectedWord [topic_id=" + topic_id + ", word=" + word + ", cn_mean=" + cn_mean + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((word == null) ? 0 : word.toLowerCase().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj)
	{
		if (this == obj)
		{
			return true;
		}
		if (obj == null)
		{
			return false;
		}
		if (getClass() != obj.getClass())
		{
			return false;
		}
		SelectedWord other = (SelectedWord) obj;
		if (!word.equalsIgnoreCase(other.word))
		{
			return false;
		}

		return true;
	}
}
