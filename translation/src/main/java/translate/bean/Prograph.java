package translate.bean;

public class Prograph {
	private String eng;
	private String chs;
	private String voice;

	public String getEng() {
		return eng;
	}

	public void setEng(String eng) {
		this.eng = eng;
	}

	public String getChs() {
		return chs;
	}

	public void setChs(String chs) {
		this.chs = chs;
	}

	public String getVoice() {
		return voice;
	}

	public void setVoice(String voice) {
		this.voice = voice;
	}

	@Override
	public String toString() {
		return "Prograph [eng=" + eng + ", chs=" + chs + ", voice=" + voice + "]";
	}
}
