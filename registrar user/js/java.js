function add(){
    var name = d1.value;
    var lname = d2.value;
    var email = d3.value;
    var age = d4.value;
    
    var x = {
    Name: name,
    LName: lname, 
    Email: email, 
    Age: age
  };
  a.innerHTML = "<td>"+x.Name+"</td><td>"+x.LName+"</td><td>"+x.Email+"</td><td>"+x.Age+"</td>";
  }