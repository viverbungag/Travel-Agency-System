import java.io.*;
import java.util.*;
/*************************************************************************************************************
 * 
 * Created By: Viver Val Bungag & Julienne Andrea Panes
 * 
 * Object Oriented Programming FSA
 *
 * Travel Agency Program
 * Description: [The program would be used by people who does business related to a travel guide or by Travel Agencies. 
 * Our program could record bookings and store it in a txt file which serves as our database. 
 * Before booking, our program would require adding destinations, then adding transport options for that specific destination, then adding travelers who would avail.
 * Our program also has a functionality of adding, deleting, viewing, and editing.] 
 * 
 * Topics included: [Polymorphism, Encapsulation, Inheritance]
 * 
 */

public class Main {
	static String separator = "\n------------------------------------------------------------------------------------------";
	static ArrayList<Integer> transDelID = new ArrayList<Integer>();
	static ArrayList<Destination> destinationList = new ArrayList<Destination>();
	static ArrayList<TransportOption> transportOptionList = new ArrayList<TransportOption>();
	static ArrayList<TravelBuddiesGroup> travelBuddiesList = new ArrayList<TravelBuddiesGroup>();
	static ArrayList<String> bookingsList = new ArrayList<String>();
	static ArrayList<Integer> deletedDescID = new ArrayList<Integer>();
	static ArrayList<Integer> deletedTransID = new ArrayList<Integer>();
	static ArrayList<Integer> deletedGroupID = new ArrayList<Integer>();
	static ArrayList<ArrayList<Integer>> deletedTransCls = new ArrayList<ArrayList<Integer>>();
	static int toLoadCheck;

	static Scanner in = new Scanner(System.in);
	public static void main(String[] args) throws IOException {
		loadAllDatabase();
		while (true){
			try{
				System.out.print("\n\tWelcome to our Travel Agency\n============================================\nPlease Choose a number you want to access to:\n1.Destination\n2.Travelers\n3.Transport Option\n4.Book a traveler\n5.Summary\n6.Exit\nEnter your number: ");
				int decision = Integer.parseInt(in.nextLine());
				if (decision == 1){
					while(true){
						System.out.print("\nWhat action do you want to perform?\n1.Add Destination\n2.View Destination\n3.Delete Destination\n4.Edit Destination\n5.Back\nEnter your number: ");
						int decision2 = Integer.parseInt(in.nextLine());
						//
						if (decision2 == 1){
							addDestination();
						}
						else if (decision2 == 2){
							viewDestination();
						}
						else if (decision2 == 3){
							deleteDestination();
						}
						else if (decision2 == 4){
							editDestination();
						}
						else if (decision2 == 5){
							break;
						}
						else{
							System.out.println("Please Input a number within the range only");
						}
					}
				}
				else if (decision == 2){
					while(true){
						System.out.print("\nWhat action do you want to perform?\n1.Add Travelers\n2.View Travelers\n3.Delete Travelers\n4.Edit Travelers\n5.Back\nEnter your number: ");
						int decision2 = Integer.parseInt(in.nextLine());

						if (decision2 == 1){
							addTravelers();
						}
						else if (decision2 == 2){
							viewTravelers();
						}
						else if (decision2 == 3){
							deleteTravelers();
						}
						else if (decision2 == 4){
							editTravelers();
						}
						else if (decision2 == 5){
							break;
						}
						else{
							System.out.println("Please Input a number within the range only");
						}
					}
				}
				else if (decision == 3){
					while(true){
						System.out.print("\nWhat action do you want to perform?\n1.Add Transport Option\n2.View Transport Option\n3.Delete Transport Option\n4.Edit Transport Option\n5.Back\nEnter your number: ");
						int decision2 = Integer.parseInt(in.nextLine());

						if (decision2 == 1){
							addTransportOption();
						}
						else if (decision2 == 2){
							viewTransportOption();
						}
						else if (decision2 == 3){
							deleteTransportOption();
						}
						else if (decision2 == 4){
							editTransportOption();
						}
						else if (decision2 == 5){
							break;
						}
						else{
							System.out.println("Please Input a number within the range only");
						}
					}
				}
				else if (decision == 4){
					while(true){
						toLoadCheck = 1;
						System.out.print("\nWhat action do you want to perform?\n1.Add Booking\n2.View Bookings\n3.Back\nEnter your number: ");
						int decision2 = Integer.parseInt(in.nextLine());

						if (decision2 == 1){
							addBooking();
						}
						else if (decision2 == 2){
							viewBooking();
						}
						else if (decision2 == 3){
							break;
						}
						else{
							System.out.println("Please Input a number within the range only");
						}
					}	
				}
				else if (decision == 5){
					viewSummary();
				}
				else if (decision == 6){
					System.out.println("\nThank you for using our program");
					break;
				}
				else{
					System.out.println("Please Input a number within the range only");
				}				
			}catch(NumberFormatException ex){
				System.out.println("Please Input a number only");
			}
		}
	}

	static void loadAllDatabase(){
		System.out.println("............");
		System.out.println("-> Database is now loaded");
		toLoadCheck = 0;
		destinationLoadCSV();
		deletedDestinationIdLoadCSV();
		transportLoadCSV();
		deletedtransportIdLoadCSV();
		deletedtransportClsLoadCSV();
		TravelBuddiesLoadCSV();
		deletedTravelBuddiesIdLoadCSV();
		bookingsLoadCSV();
		
	}

	//destination
	static void addDestination(){
		int id = 0;
		System.out.print("Enter destination name: ");
		String name = in.nextLine();
		System.out.print("Enter destination description: ");
		String description = in.nextLine();
		System.out.print("Enter destination location: ");
		String location = in.nextLine();
		if(deletedDescID.size() < 1){
			id = destinationList.size() + 1;
		}else{
			id = deletedDescID.get(0);
			deletedDescID.remove(0);
		}
		destinationList.add(new Destination(id, name, description, location));
		deletedTransCls.add(new ArrayList<Integer>());
		destinationList.sort(new sortKeyDestination());
		destinationSaveCSV();
		deletedDestinationIdSaveCSV();
		System.out.println("\nSuccessfully added the " + name + " Destination");
		viewDestination();
	}
	static void viewDestination(){
		if (destinationList.size() > 0){
			System.out.println(separator);
			for(Destination dest: destinationList){
				dest.viewState();
			}
			System.out.println(separator);
		}else{
			System.out.println("\nThere are currently no Destinations");
		}
	}
	static void deleteDestination(){
		Iterator<TransportOption> transportOptionListIT = transportOptionList.iterator();
		Iterator<TravelBuddiesGroup> travelBuddiesListIT = travelBuddiesList.iterator();
		Iterator<String> bookingsListIT = bookingsList.iterator();


		if (destinationList.size() > 0){
			System.out.println("List of destinations:");
			int count = 1;
			for (Destination dest: destinationList){
				System.out.println(String.valueOf(count) + "." +dest.name);
				count++;
			}
			while(true){
				try{
					System.out.print("Choose a number to delete: ");
					int deleteNum = Integer.parseInt(in.nextLine());
					String name = destinationList.get(deleteNum-1).name;
					int id = destinationList.get(deleteNum-1).destinationCount;
					deletedDescID.add(id);

					//deleting the travelers associated with the destination
					while (travelBuddiesListIT.hasNext()){
						TravelBuddiesGroup tb = travelBuddiesListIT.next();
						if (id == tb.destination.destinationCount){
							deletedGroupID.add(tb.id);
							tb.deleteTransportOption();
							travelBuddiesListIT.remove();
						}
					}

					//deleting transport options associated with the destination
					while (transportOptionListIT.hasNext()){
						TransportOption to = transportOptionListIT.next();
						if (id == to.destination.destinationCount){
							int id2 = to.id;

							//removing the bookings associated with the transport options
							for (TravelBuddiesGroup tb: to.groups){

								Iterator<TransportOption> bookingsIT = tb.bookings.iterator();
								while(bookingsIT.hasNext()){
									TransportOption booking = bookingsIT.next();
									if(booking.name.equals(name)){
										bookingsIT.remove();
									}
								}

								bookingsIT.forEachRemaining(tb.bookings::add);
							}

							//removing the bookingsList arraylist elements associated to the transport options
							while (bookingsListIT.hasNext()){
								String bookingL = bookingsListIT.next();
								String[] splitted = bookingL.split(" , ");
								if (String.valueOf(id2).equals(splitted[1])){
									bookingsListIT.remove();	
								}
							}

							transportOptionListIT.remove();
							deletedTransID.add(to.id);

						}
					}

					deletedTransID = removeDuplicates(deletedTransID);
					deletedGroupID = removeDuplicates(deletedGroupID);
					deletedDescID = removeDuplicates(deletedDescID);
					transportOptionListIT.forEachRemaining(transportOptionList::add);
					travelBuddiesListIT.forEachRemaining(travelBuddiesList::add);
					bookingsListIT.forEachRemaining(bookingsList::add);
					deletedTransCls.remove(deleteNum-1);
					destinationList.remove(deleteNum-1);
					Collections.sort(deletedTransID);
					Collections.sort(deletedGroupID);
					Collections.sort(deletedDescID);
					destinationSaveCSV();
					deletedDestinationIdSaveCSV();
					transportSaveCSV();
					deletedtransportIdSaveCSV();
					TravelBuddiesSaveCSV();
					deletedTravelBuddiesIdSaveCSV();
					bookingsSaveCSV();
					System.out.println("\nSuccessfully deleted the " + name + " Destination");
					viewDestination();
					break;

				}catch(Exception ex){
					ex.printStackTrace();
					System.out.println("Please input values within range only");
				}
			}
		}else{
			System.out.println("\nThere are no Destinations to delete");
		}
	}
	static void editDestination(){
		if (destinationList.size() > 0){
			System.out.println("List of destinations:");
			int count = 1;
			for (Destination dest: destinationList){
				System.out.println(String.valueOf(count) + "." + dest.name);
				count++;
			}
			while(true){
				try{	
					System.out.print("Choose a number to edit: ");
					int editNum = Integer.parseInt(in.nextLine());
					Destination currDest = destinationList.get(editNum-1);
					String name = currDest.name;
					System.out.print("Enter destination name: ");
					currDest.name = in.nextLine();
					System.out.print("Enter destination description: ");
					currDest.description = in.nextLine();
					System.out.print("Enter destination location: ");
					currDest.location = in.nextLine();
					destinationSaveCSV();
					System.out.println("\nSuccessfully edited the " + name + " Destination");
					viewDestination();
					break;

				}catch(Exception ex){
					System.out.println("\nPlease input values within range only");
				}
			}
		}else{
			System.out.println("\nThere are no Destinations to edit");
		}
	}
	//transport option
	static void addTransportOption(){
		Destination currDest = null;
		String destName = null;
		int choice = 0;
		if(destinationList.size() > 0){
			int count = 1;
			for (Destination dest: destinationList){
				System.out.println(String.valueOf(count) + ". " + dest.name);
				count++;
			}
			try{
				System.out.print("Choose a destination for this option: ");
				choice = Integer.parseInt(in.nextLine());
				currDest = destinationList.get(choice-1);
				destName = currDest.name;
			}catch(Exception ex){
				System.out.println("\nPlease input values within range only");
			}
			
			while(true){
				try{
					System.out.print("Enter transport option name: ");
					String name = in.nextLine();
					System.out.print("Enter transport option maximum capacity: ");
					int capacity = Integer.parseInt(in.nextLine());
					System.out.print("Enter transport option cost: ");
					float cost = Float.parseFloat(in.nextLine());
					int cls = 1;
					int id = 1;

					if (currDest.destinationTOptions.size() > 0){
						if (deletedTransCls.get(choice-1).size() > 0){
							cls = deletedTransCls.get(choice-1).get(0);
							deletedTransCls.get(choice-1).remove(0);
						}else{
							cls = currDest.destinationTOptions.size() + 1;
						}
						
					}
					if (transportOptionList.size() > 0){
						if (deletedTransID.size() > 0){
							id = deletedTransID.get(0);
							deletedTransID.remove(0); 
						}else{
							id = transportOptionList.size() + 1;
						}
					}

					TransportOption option = new TransportOption(id, name, cls, cost, capacity, currDest);
					transportOptionList.add(option);
					transportOptionList.sort(new sortKeyTransport());
					transportSaveCSV();
					deletedtransportIdSaveCSV();
					deletedtransportClsSaveCSV();
					System.out.println("\n==Creating travel buddies group...");
        			System.out.println("==" + name + ": Setting destination '" + currDest.name + "'");
					System.out.println("\nSuccessfully added " + name + " Transport Option to Destination " + destName);
					viewTransportOption();
					break;
				}catch(Exception ex){
					System.out.println("\nPlease input correct values");
				}
			}
		}else{
			System.out.println("\nPlease input a destination first before inputting a transport option");
		}
	}
	static void viewTransportOption(){
		System.out.println(separator);
		if (transportOptionList.size() > 0){
			for (TransportOption option: transportOptionList){
				option.viewState();
			}
		}else{
			System.out.println("\nThere are currently no Transport Options");
		}
		System.out.println(separator);
	}
	static void deleteTransportOption(){
		if (transportOptionList.size() > 0){
			int count = 1;

			for (TransportOption option: transportOptionList){
				System.out.println(String.valueOf(count) + "." + option.name + " - (" + option.destination.name + ", " + option.destination.location + ")");
				count++;
			}

			while(true){
				try{
					System.out.print("Choose a Travel Option to delete: ");
					int delNum = Integer.parseInt(in.nextLine());
					String name = transportOptionList.get(delNum-1).name;
					int id = transportOptionList.get(delNum-1).id;
					int cls = transportOptionList.get(delNum-1).cls;
					String destName = transportOptionList.get(delNum-1).destination.name;
					int destId = transportOptionList.get(delNum-1).destination.destinationCount;

					//delete bookings of the group that was deleted
					for (TravelBuddiesGroup tb: transportOptionList.get(delNum-1).groups){
						Iterator<TransportOption> bookingsIT = tb.bookings.iterator();

						while(bookingsIT.hasNext()){
							TransportOption booking = bookingsIT.next();
							if(booking.name.equals(name)){
								bookingsIT.remove();
							}
						}
						bookingsIT.forEachRemaining(tb.bookings::add);
					}

					//delete from bookingsList
					Iterator<String> bookingsListIT = bookingsList.iterator();
					while (bookingsListIT.hasNext()){
						String bookingL = bookingsListIT.next();
						String[] splitted = bookingL.split(" , ");
						if (String.valueOf(id).equals(splitted[1])){
							bookingsListIT.remove();	
						}
					}

					deletedTransID = removeDuplicates(deletedTransID);
					bookingsListIT.forEachRemaining(bookingsList::add);
					deletedTransID.add(id);
					deletedTransCls.get(destId-1).add(cls);
					transportOptionList.get(delNum-1).delete();
					transportOptionList.remove(delNum-1);
					transportSaveCSV();
					deletedtransportIdSaveCSV();
					deletedtransportClsSaveCSV();
					bookingsSaveCSV();
					System.out.println("\nSuccessfully deleted the " + name + " Transport Option from Destination " + destName);
					viewTransportOption();
					break;
				}catch(Exception ex){
					System.out.println("\nPlease input values within range only");
				}
			}

		}else{
			System.out.println("\nThere are currently no Travel Options to delete");
		}
	}
	static void editTransportOption(){
		if (transportOptionList.size() > 0){
			int count = 1;
			TransportOption currOpt = null;
			for (TransportOption option: transportOptionList){
				System.out.println(String.valueOf(count) + "." + option.name);
				count++;
			}
			while (true){
				try{
					System.out.print("Choose a Travel Option to edit: ");
					int editNum = Integer.parseInt(in.nextLine());
					currOpt = transportOptionList.get(editNum-1);
					break;
				}catch(Exception ex){
					System.out.println("\nPlease input values within range only");
				}
			}
			while(true){
				try{
					String name = currOpt.name;
					String destName = currOpt.destination.name;
					System.out.print("Enter transport option name: ");
					currOpt.name = in.nextLine();
					System.out.print("Enter transport option maximum capacity: ");
					currOpt.upperLimit = Integer.parseInt(in.nextLine());
					System.out.print("Enter transport option budget: ");
					currOpt.cost = Float.parseFloat(in.nextLine());
					currOpt.update();
					transportSaveCSV();
					System.out.println("\nSuccessfully edited the " + name + " Transport Option to Destination " + destName);
					viewTransportOption();
					break;
				}catch(Exception ex){
					System.out.println("\nPlease input correct values");
				}
			}
		}else{
			System.out.println("\nThere are currently no Travel Options to edit");
		}
	}
	//travellers
	static void addTravelers(){
		Destination currDest = null;
		if (destinationList.size() > 0){
			System.out.println("\nList of destinations:");
			int count = 1;
			for (Destination dest: destinationList){
				System.out.println(String.valueOf(count) + "." + dest.name);
				count++;
			}
			while(true){
				try{	
					System.out.print("Choose a destination for the traveler: ");
					int choice = Integer.parseInt(in.nextLine());
					currDest = destinationList.get(choice-1);
					break;
				}catch(Exception ex){
					System.out.println("\nPlease input values within range only");
				}
			}

			while(true){
				try{
					System.out.print("Enter team name: ");
					String name = in.nextLine();
					System.out.print("Enter number of members: ");
					int mCount = Integer.parseInt(in.nextLine());
					System.out.print("Enter budget: ");
					float budget = Float.parseFloat(in.nextLine());
					int id = 1;

					if (deletedGroupID.size() < 1){
						id = travelBuddiesList.size() + 1;
					}else{
						id = deletedGroupID.get(0);
						deletedGroupID.remove(0);
					}

					TravelBuddiesGroup group = new TravelBuddiesGroup(id, name, mCount, budget, currDest);
					travelBuddiesList.add(group);
					travelBuddiesList.sort(new sortKeyGroup());
					TravelBuddiesSaveCSV();
					deletedTravelBuddiesIdSaveCSV();
					System.out.println("\nSuccessfully added the " + name + " Traveler Group");
					viewTravelers();
					break;
				}catch(Exception ex){
					ex.printStackTrace();
					System.out.println("\nPlease input correct values");
				}
			}
			
		}else{
			System.out.println("\nPlease input a destination first before inputting travlers");
		}
	}
	static void viewTravelers(){
		System.out.println(separator);
		if (travelBuddiesList.size() > 0){
			for (TravelBuddiesGroup group: travelBuddiesList){
				group.viewState();
			}
			System.out.println(separator);
		}else{
			System.out.println("\nThere are currently no Travelers");
		}
	}

	static void deleteTravelers(){
		if (travelBuddiesList.size() > 0){
			System.out.println("List of Travelers:");
			int count = 1;
			for (TravelBuddiesGroup group: travelBuddiesList){
				System.out.println(String.valueOf(count) + "." + group.group);
				count++;
			}
			while(true){
				try{

					deletedGroupID = removeDuplicates(deletedGroupID);
					System.out.print("Choose a number to delete: ");
					int deleteNum = Integer.parseInt(in.nextLine());
					String name = travelBuddiesList.get(deleteNum-1).group;
					int id = travelBuddiesList.get(deleteNum-1).id;
					deletedGroupID.add(id);
					travelBuddiesList.get(deleteNum-1).deleteTransportOption();
					travelBuddiesList.remove(deleteNum-1);
					Collections.sort(deletedGroupID);
					TravelBuddiesSaveCSV();
					deletedTravelBuddiesIdSaveCSV();
					bookingsSaveCSV();
					System.out.println("\nSuccessfully deleted the " + name + " Traveler Group");
					viewTravelers();
					break;
				}catch(Exception ex){
					System.out.println("\nPlease input values within range only");
				}
			}
		}else{
			System.out.println("\nThere are currently no Travelers to delete");
		}
	}

	static void editTravelers(){
		if (travelBuddiesList.size() > 0){
			System.out.println("List of Travelers:");
			int count = 1;
			TravelBuddiesGroup currTrav = null;
			for (TravelBuddiesGroup group: travelBuddiesList){
				System.out.println(String.valueOf(count) + "." + group.group);
				count++;
			}

			while(true){
				try{
					System.out.print("Choose a number to edit: ");
					int editNum = Integer.parseInt(in.nextLine());
					currTrav = travelBuddiesList.get(editNum-1);
					break;
				}catch(Exception ex){	
					System.out.println("\nPlease input values within range only");
				}
			}
			while(true){
				try{
					String name = currTrav.group;
					System.out.print("Enter team name: ");
					currTrav.group = in.nextLine();
					System.out.print("Enter number of members: ");
					currTrav.members = Integer.parseInt(in.nextLine());
					System.out.print("Enter budget: ");
					currTrav.budget = Float.parseFloat(in.nextLine());
					TravelBuddiesSaveCSV();
					System.out.println("\nSuccessfully edited the " + name + " Traveler Group");
					viewTravelers();
					break;
				}catch(Exception ex){
					System.out.println("\nPlease input correct values");
				}
			}
		}else{
			System.out.println("\nThere are currently no Travelers to edit");
		}
	}

	//bookings
	static void addBooking(){
		System.out.println("\n----------------------------------------------------------------");
		System.out.println("Starting booking process...");
		System.out.println("----------------------------------------------------------------\n");

		System.out.println("\n***NOTE: YOU CANNOT DELETE OR EDIT A BOOKING TO PRESERVE CREDIBILITY, SO PLEASE BE SURE ON YOUR INPUTS\n");

		if (travelBuddiesList.size() > 0){
			if (transportOptionList.size() > 0){
				System.out.println("List of Travelers:");
				int count = 1;
				TravelBuddiesGroup currTrav = null;
				for (TravelBuddiesGroup group: travelBuddiesList){
					System.out.println(String.valueOf(count) + "." + group.group);
					count++;
				}

				while(true){
					try{
						System.out.print("Choose a travler to book: ");
						int choice = Integer.parseInt(in.nextLine());
						currTrav = travelBuddiesList.get(choice-1);
						break;
					}catch(Exception ex){
						System.out.println("\nPlease input values within range only");
					}
				}
				count = 1;
				for (TransportOption option: currTrav.destination.destinationTOptions){
					System.out.println(String.valueOf(count) + "." + option.name + " - (" + option.destination.name + ", " + option.destination.location + ") - Capacity: " + option.upperLimit + " pax");
					count++;
				}
				while(true){
					try{
						System.out.print("Choose a number for booking: ");
						int choice = Integer.parseInt(in.nextLine());
						boolean valid = currTrav.addTransportOption(choice);
						if (valid) {	
							
							bookingsList.add(currTrav.id + " , " + currTrav.destination.destinationTOptions.get(choice-1).id);
							bookingsSaveCSV();
							System.out.println("\nSuccessfully booked " + currTrav.destination.destinationTOptions.get(choice-1).name + " for " + currTrav.group);
						}else{
							System.out.println("Booking was not successful");
						}
						break;
					}catch(Exception ex){
						System.out.println("\nPlease input values within range only");
					}
				}
			}else{
				System.out.println("\nThere are currently no Transport Options to be picked");
			}
		}else{
			System.out.println("\nThere currently no travelers to be booked");
		}
	}

	static void viewBooking(){
		if (travelBuddiesList.size() > 0){
			System.out.println("\nList of Travelers:");
			int count = 1;
			TravelBuddiesGroup currTrav = null;
			for (TravelBuddiesGroup group: travelBuddiesList){
				System.out.println(String.valueOf(count) + "." + group.group);
				count++;
			}
			while(true){
				try{
					System.out.print("Choose a traveller to view: ");
					int choice = Integer.parseInt(in.nextLine());
					currTrav = travelBuddiesList.get(choice-1);
					break;
				}catch(Exception ex){
					System.out.println("\nPlease input values within range only");
				}
			}
			if (currTrav.bookings.size() > 0){
				count = 1;
				System.out.println("\nBookings for " + currTrav.group + ":");
				for (TransportOption book: currTrav.bookings){
					System.out.println(String.format("\t%d. %s - %.2f", count, book.name, book.cost));
					count++;
				}	
			}else{
				System.out.println("\nThere are currently no bookings for this traveler");
			}
		}else{
			System.out.println("\nThere are currently no travelers to be viewed");
		}
	}

	static void viewSummary(){
		System.out.println("\n----------------------------------------------------------------");
		System.out.println("                           SUMMARY");
		System.out.println("----------------------------------------------------------------\n");
		System.out.println("================================ Destinations ================================");
		viewDestination();
		System.out.println("\n================================ Transport Options ================================");
		viewTransportOption();
		System.out.println("\n================================ Travelers ================================");
		viewTravelers();
	}


	static void destinationSaveCSV(){

        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("destinations.txt"));
            for(Destination d: destinationList){
                String[] values = {String.valueOf(d.destinationCount), d.name, d.description, d.location};
                String line = String.join(" , ", values);
                bw.append(line);
                bw.append("\n");
            }
            bw.close();

        }catch(Exception e){

        }
    }

	static void destinationLoadCSV(){

		try{
			String[] values = {};
			BufferedReader br = new BufferedReader(new FileReader("destinations.txt"));
			String line;
			while((line=br.readLine()) != null){
				values = line.split(" , ");
				int id = Integer.valueOf(values[0]);
				String name = values[1];
				String desc = values[2];
				String loc = values[3];
				Destination d = new Destination(id, name, desc, loc);
				destinationList.add(d);
				deletedTransCls.add(new ArrayList<Integer>());
			}
			br.close();

		}catch(Exception ex){
		}

    }

	static void deletedDestinationIdSaveCSV(){

		try{                                   
            BufferedWriter bw = new BufferedWriter(new FileWriter("destination deleted ID.txt", false));
            StringBuilder sb = new StringBuilder();
			
            for(int id: deletedDescID){
                sb.append(id);
                sb.append(" , ");
            }
            bw.write(sb.toString());
            bw.close();
        }catch (Exception e){

		}
	}

	static void deletedDestinationIdLoadCSV(){

		try{
            BufferedReader br = new BufferedReader(new FileReader("destination deleted ID.txt"));
			String line = br.readLine();
			String[] IDs = line.split(" , ");
            for(String id: IDs){
				deletedDescID.add(Integer.parseInt(id));
            }
            br.close();
        }catch (Exception e){
        }
	}


	static void transportSaveCSV(){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("transport options.txt"));
            for(TransportOption t: transportOptionList){
                String[] values = {String.valueOf(t.id), t.name, String.valueOf(t.cls), String.valueOf(t.cost), String.valueOf(t.upperLimit), String.valueOf(t.destination.destinationCount)};
                String line = String.join(" , ", values);
                bw.append(line);
                bw.append("\n");
            }
            bw.close();
        }catch(Exception e){
        }
	}

	static void transportLoadCSV(){
        
        try{
            String line;
            BufferedReader br = new BufferedReader(new FileReader("transport options.txt"));

            while((line=br.readLine()) != null){
                String[] values = line.split(" , ");
                int id = Integer.valueOf(values[0]);
                String name = values[1];
                int cls = Integer.valueOf(values[2]);
                float cost = Float.valueOf(values[3]);
                int max = Integer.valueOf(values[4]);
				int destId = Integer.valueOf(values[5]);
				Destination dest = destinationList.get(destId-1);

				transportOptionList.add(new TransportOption(id, name, cls, cost, max, dest));

            }
            br.close();
        }catch(Exception ex){
        }
    }

	static void deletedtransportIdSaveCSV(){
		try{                                   
            BufferedWriter bw = new BufferedWriter(new FileWriter("transport option deleted ID.txt", false));
            StringBuilder sb = new StringBuilder();
			
            for(int id: deletedTransID){
                sb.append(id);
                sb.append(" , ");
            }
            bw.write(sb.toString());
            bw.close();
        }catch (Exception e){

        }
	}

	static void deletedtransportIdLoadCSV(){
		try{
            BufferedReader br = new BufferedReader(new FileReader("transport option deleted ID.txt"));
			String line = br.readLine();
			String[] IDs = line.split(" , ");
            for(String id: IDs){
				deletedTransID.add(Integer.parseInt(id));
            }
            br.close();
        }catch (Exception e){

        }
	}

	static void deletedtransportClsSaveCSV(){
        try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("transport options deleted cls.txt"));
            for(ArrayList<Integer> dtc: deletedTransCls){
				Object[] objects = dtc.toArray();
				String line = String.join(" , ", objects.toString());
                bw.append(line);
                bw.append("\n");
            }
            bw.close();
        }catch(Exception e){
        }
	}

	static void deletedtransportClsLoadCSV(){
        try{
            String line;
            BufferedReader br = new BufferedReader(new FileReader("transport options deleted cls.txt"));
			int count = 0;
            while((line=br.readLine()) != null){
                String[] values = line.split(" , ");
				for (String val: values){
					deletedTransCls.get(count).add(Integer.parseInt(val));
				}
				count ++;
            }
            br.close();
        }catch(Exception e){
        }
	}

	static void TravelBuddiesSaveCSV(){
		try{
            BufferedWriter bw = new BufferedWriter(new FileWriter("travel buddies group.txt"));
            for(TravelBuddiesGroup tb: travelBuddiesList){
                String[] values = {String.valueOf(tb.id), tb.group, String.valueOf(tb.members), String.valueOf(tb.budget), String.valueOf(tb.destination.destinationCount)};
                String line = String.join(" , ", values);
                bw.append(line);
                bw.append("\n");
            }
            bw.close();
        }catch(Exception e){
        }
	}

	static void TravelBuddiesLoadCSV(){
        try{
            String line;
            BufferedReader br = new BufferedReader(new FileReader("travel buddies group.txt"));

            while((line=br.readLine()) != null){
                String[] values = line.split(" , ");
				int id = Integer.parseInt(values[0]);
				String group = values[1];
				int members = Integer.parseInt(values[2]);
				float budget = Float.parseFloat(values[3]);
				int destId = Integer.valueOf(values[4]);
				Destination dest = destinationList.get(destId-1);

				travelBuddiesList.add(new TravelBuddiesGroup(id, group, members, budget, dest));
            }

            br.close();
        }catch(Exception ex){

        }
    }

	static void deletedTravelBuddiesIdSaveCSV(){

		try{                                   
            BufferedWriter bw = new BufferedWriter(new FileWriter("travel buddies deleted ID.txt", false));
            StringBuilder sb = new StringBuilder();
			
            for(int id: deletedGroupID){
                sb.append(id);
                sb.append(" , ");
            }
            bw.write(sb.toString());
            bw.close();
        }catch (Exception e){

        }
	}

	static void deletedTravelBuddiesIdLoadCSV(){

		try{
            BufferedReader br = new BufferedReader(new FileReader("transport option deleted ID.txt"));
			String line = br.readLine();
			String[] IDs = line.split(" , ");
            for(String id: IDs){
				deletedGroupID.add(Integer.parseInt(id));
            }
            br.close();
        }catch (Exception e){

        }

	}

	static void bookingsSaveCSV(){
		try{                                                                       
            BufferedWriter bw = new BufferedWriter(new FileWriter("bookings.txt"));
                for(String bl: bookingsList){
                    bw.append(bl);
                    bw.append("\n");
                }
            bw.close();
        }catch(Exception e){
        }
	}

	static void bookingsLoadCSV(){
		try{
            String line;
            BufferedReader br = new BufferedReader(new FileReader("bookings.txt"));
            while((line=br.readLine()) != null){
                String[] values = line.split(" , ");
                int c1 = Integer.parseInt(values[0]);
                int c2 = Integer.parseInt(values[1]);
                TravelBuddiesGroup tb = travelBuddiesList.get(c1-1);
                bookingsList.add(String.valueOf(c1) + " , " + String.valueOf(c2));
                tb.addTransportOption(tb.destination.destinationTOptions.get(c2-1).cls);
            }
            br.close();
        }catch(Exception e){
        }
	}

	static ArrayList<Integer> removeDuplicates(ArrayList<Integer> list){
		Set<Integer> s = new LinkedHashSet<>(list);
		return new ArrayList<Integer>(s);
	}

}

class sortKeyDestination implements Comparator<Destination> {

    public int compare(Destination a, Destination b){

        int idOrder = a.destinationCount - b.destinationCount;
		return idOrder;
    }
}

class sortKeyTransport implements Comparator<TransportOption> {

    public int compare(TransportOption a, TransportOption b){

        int idOrder = a.id - b.id;
		return idOrder;
    }
}

class sortKeyGroup implements Comparator<TravelBuddiesGroup> {

    public int compare(TravelBuddiesGroup a, TravelBuddiesGroup b){

        int idOrder = a.id - b.id;
		return idOrder;
    }
}



	