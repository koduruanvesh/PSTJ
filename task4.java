import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class EventProcessor {

    static class Event {
        String name;
        LocalDate date;

        Event(String name, LocalDate date) {
            this.name = name;
            this.date = date;
        }

        @Override
        public String toString() {
            return name;
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);


        if (!scanner.hasNextInt()) {
            scanner.close();
            return;
        }
        int numEvents = scanner.nextInt();
        List<Event> events = new ArrayList<>();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < numEvents; i++) {
            String name = scanner.next();
            String dateStr = scanner.next();
           
            LocalDate date = LocalDate.parse(dateStr, formatter);
            events.add(new Event(name, date));
        }

        int targetMonth = scanner.nextInt();
        scanner.close();

 
        List<Event> sortedEvents = events.stream()
                .sorted(Comparator.comparing(event -> event.date))
                .collect(Collectors.toList());

        if (!sortedEvents.isEmpty()) {
            Event earliestEvent = sortedEvents.get(0);
            Event latestEvent = sortedEvents.get(sortedEvents.size() - 1);

           
            List<Event> eventsInMonth = events.stream()
                    .filter(event -> event.date.getMonthValue() == targetMonth)
                    .collect(Collectors.toList());

           
            for (Event event : sortedEvents) {
                System.out.println(event.name);
            }
            System.out.println(earliestEvent.name);
            System.out.println(latestEvent.name);
            for (Event event : eventsInMonth) {
                System.out.println(event.name);
            }
        }
    }
}
