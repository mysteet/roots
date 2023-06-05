package vlad110kg.web.api.map;

public interface MapClient {

    AddressDto findAddressByCoordinates(Double lat, Double lng);
}
