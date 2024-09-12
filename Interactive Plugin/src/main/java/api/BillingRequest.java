package api;

public class BillingRequest {
    private String step;
    private String lang;
    private String code;

    public BillingRequest(String step, String lang, String code) {
        this.step = step;
        this.lang = lang;
        this.code = code;
    }

    // Getters and setters
    public String getStep() {
        return step;
    }

    public void setStep(String step) {
        this.step = step;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}