import java.util.ArrayList;

public class parentC {
    
    int destinationCount;
    int id;
    String name;
    String description;
	String location;
    int cls;
	float cost;
	Destination destination;
	int upperLimit;
    int packages = 0;
    float total = 0;
    String group;
    int members;
    float budget;
    float totalCost = 0;
    
    ArrayList<TransportOption> destinationTOptions = new ArrayList<TransportOption>();

    void viewState(){
		System.out.println("\n------------ DESTINATION INFO -------------");
		System.out.println("Destination ID: " + destinationCount);
		System.out.println("Name: " + name);
		System.out.println("Description: " + description);
		System.out.println("Location: " + location);
		System.out.println("Transport options: ");
		
		if (destinationTOptions.size() == 0){
			System.out.println("\tNo entry yet.");
		}
		
		int count = 0;
		for(TransportOption t: destinationTOptions){
			System.out.println(String.format("%d. %s = Class %d (Cost: P%.1f)",count+1, t.name, t.cls, t.cost));

			count++;
		}
    }
}
