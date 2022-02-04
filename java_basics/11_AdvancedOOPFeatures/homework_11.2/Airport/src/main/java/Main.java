import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Main {
    public static void main(String[] args) {


    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        Date deadLine = new Date( new Date().getTime() + 1000 * 60 * 60 * 2);
        List<Flight> departsFlights = new ArrayList<>();

        for (Terminal terminal: airport.getTerminals())
            terminal.getFlights().stream()
                    .filter(flight -> flight.getType().equals(Flight.Type.DEPARTURE))
                    .filter(flight -> flight.getDate().before(deadLine))
                    .forEach(departsFlights::add);

        return departsFlights;
    }

}