import java.util.*;

public class TransportOption extends parentC {
	
	ArrayList<TravelBuddiesGroup> groups = new ArrayList<TravelBuddiesGroup>();


    public TransportOption(int id, String name, int cls, float cost, int maximumCapacity, Destination destination){
		this.id = id;
		this.name = name;
		this.cls = cls;
		this.cost = cost;
		this.upperLimit = maximumCapacity;
		this.destination = destination;

		destination.destinationTOptions.add(this);
	}	

	@Override
	void viewState(){
		System.out.println("\n------------ TRANSPORT OPTION -------------");
		System.out.println("Transport Option Id: " + id);
		System.out.println("Location: " + destination.location);
		System.out.println("Name: " + name);
		System.out.println("Cost: " + cost);
		System.out.println("Maximum Capacity: " + upperLimit + " pax");

		if (groups.size() == 0){
			System.out.println("\tNo entry yet.");
		}

		for (int x = 0; x < groups.size(); x++){
			System.out.println(String.format("%d. %s = %d (members count)", x+1, groups.get(x).group, groups.get(x).members));
		}
	}
	
	void delete(){
		destination.destinationTOptions.remove(cls-1);
	}
	
	void update(){
		destination.destinationTOptions.set(cls-1, this);
	}

	void book(TravelBuddiesGroup group, int packageCount){
		for (int x = 0; x < packageCount; x++){
			groups.add(group);
		}
	}

	int getPackageCount(int memberCount){
		return (int) Math.ceil((float)memberCount / upperLimit);
	}

	void deleteBook(TravelBuddiesGroup groupPassed){
		groups.remove(groupPassed);
	}
}
