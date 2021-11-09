import java.util.*;

public class TravelBuddiesGroup extends parentC{

    ArrayList<TransportOption> bookings = new ArrayList<TransportOption>();

    public TravelBuddiesGroup(int id, String group, int members, float budget, Destination destination){
        this.id = id;
        this.group = group;
        this.members = members;
        this.budget = budget;
        this.destination = destination;
    }

    @Override
    void viewState(){

        System.out.println("\n------------ TRAVEL BUDDIES -------------");
        System.out.println("Group ID: " + id);
        System.out.println("Group Name: " + group);
        System.out.println("Destination: " + destination.name);
        System.out.println("No. of members: " + members);
        System.out.println("Budget: P" + budget);
        System.out.println("Amount remaining: P" + (budget-totalCost));
        System.out.println("Activities:");
        
        if (bookings.size() == 0){
            System.out.println("\tNo entry yet.");
        }

        for (int x = 0; x < bookings.size(); x++){
            System.out.println(String.format("\t%d. %s - %.2f", x+1, bookings.get(x).name, bookings.get(x).cost));
        }
        
    }
    
    public boolean addTransportOption(int cls){
        int count = 0;
        
        if (cls <= destination.destinationTOptions.size()){
            for (TransportOption to: destination.destinationTOptions){
                if (cls == to.cls){
                    break;
                }
                count++;
            }

            TransportOption transOp = destination.destinationTOptions.get(count);
            String transportName = transOp.name;
            int transportMaxCap = transOp.upperLimit;
            float perCost = transOp.cost;
            int numPackage = transOp.getPackageCount(members);
            totalCost += numPackage*perCost;
            if(Main.toLoadCheck == 1){
                System.out.println(String.format("\n%s: chose transport option no. '%d' for destination %s", group, cls, destination.name));
                System.out.println(String.format("%s: Adding transport option '%s'", group, transportName));
                System.out.println(String.format("Compute for no. of packages to avail: %d(members count) divided by %d (maximum capacity/pax)", members, transportMaxCap));
                System.out.println(String.format("No. of packages %d", numPackage));
                System.out.println("Total cost: " + totalCost);
            }
            packages = numPackage;
            total = totalCost;
            if (budget > totalCost){
                for (int x = 0; x < numPackage; x++){
                    bookings.add(transOp);
                }
                transOp.book(this, numPackage);

                if(Main.toLoadCheck == 1){

                    if (numPackage > 1){
                        System.out.println(String.format("Successfully added '%s' %d times", transportName, numPackage));
                    }else{
                        System.out.println(String.format("Successfully added '%s'", transportName));
                    }
                    this.viewState();
                    
                }
                return true;
            }else{
                totalCost -= numPackage*perCost;
                System.out.println("Budget not enough!");
                return false;
            }
        }else{
            System.out.println(String.format("\n%s: chose transport option no. '%d' for destination %s", group, cls, destination.name));
            System.out.println("Cannot add transport option. Invalid option number.");
        }
        return false;
    }

    public void deleteTransportOption(){

        for (TransportOption book: bookings){
            try{
                book.deleteBook(this);
            }catch(Exception ex){
                System.out.println(ex);
            }
        }
    }
    
}
