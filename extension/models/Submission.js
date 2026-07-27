class Submission {

    constructor(date, language, verdict) {
        this.date = date;
        this.language = language;
        this.verdict = verdict;
    }
    getDate(){
        return this.date;
    }
    getLanguage(){
        return this.language;
    }
    getVerdict(){
        return this.verdict;
    }
}