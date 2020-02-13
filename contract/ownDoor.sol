pragma solidity >=0.4.22 <0.7.0;

contract OwnDoor {
    
    struct BookingInfo {
        bool isBooked;
        uint startTime;
        uint endTime;
    }
    
    struct Door {
        uint id;
        address owner;
        mapping (address => BookingInfo) bookingInfo;
    }

    Door public door;

    constructor() public {
        door.id = 1;
        door.owner = msg.sender;
    }
    
    function openDoor() public view returns(bool) {
        // require(door.id == id);
        if (door.bookingInfo[msg.sender].isBooked == true) {
            if (now < door.bookingInfo[msg.sender].endTime) {
                if (now > door.bookingInfo[msg.sender].startTime) {
                    return true;
                }
            }
        }
        return false;
    }
    
    // CURD booking
    modifier isOwner(address owner) {
        require(door.owner == owner);
        _;
    }
    function addBooking(address guest, uint startTime, uint endTime) isOwner(msg.sender) public  returns(bool) {
        door.bookingInfo[guest].isBooked = true;
        door.bookingInfo[guest].startTime = startTime;
        door.bookingInfo[guest].endTime = endTime;
        return true;
    }
    
    function removeBooking(address guest) isOwner(msg.sender) public  returns(bool) {
        door.bookingInfo[guest].isBooked = false;
        return true;
    }    
}