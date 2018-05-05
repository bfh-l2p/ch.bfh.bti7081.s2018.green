package ch.bfh.bti7081.s2018.green;

public enum PageName {

	JOURNAL("Journal"),
	MEDICATION("Medication"),
	DIAGNOSIS("Diagnosis"),
	THERAPY("Therapy");	
	
    private final String name;       

    private PageName(String name) {
        this.name = name;
    }
    
    public String getName() {
       return this.name;
    }
	
	
}
