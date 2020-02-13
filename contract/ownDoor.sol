pragma solidity >=0.4.22 <0.7.0;

// TODO
// 1. 도어락 여러개 관리할수있도록
// 2. 테스트
// 3. 타임스탬프 확인할수있도록 추가

contract OwnDoor {
    
    struct Door {
        uint id;
        address owner;
        mapping (address => bool) booked;
    }
    
    Door public door;

    constructor() {
    
    }
    
    // validate booking
    modifier validateGuest(address guest) {
        require(door.booked[guest] == true);
        _;
    }
    function openDoor(uint id)  validateGuest(msg.sender) public view returns(bool) {
        require(door.id == id);
        return true
    }
    
    
    // CRUD booking
    modifier isOwner(address owner) {
        require(door.owner == owner);
        _;
    }
    function addBooking(address guest) isOwner(msg.sender) public view returns(bool) {
        door.booked[guest] = true;
        return true;
    }
    
    function removeBooking(address guest) isOwner(msg.sender) public view returns(bool) {
        door.booked[guest] = false;
        return true;
    }    
    
}