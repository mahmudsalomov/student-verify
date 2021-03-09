function mailSender() {
    let email=document.getElementById("email").value;
    let firstname=document.getElementById("firstname").value;
    let lastname=document.getElementById("lastname").value;
    // document.getElementById("button").style.visibility = 'hidden';

    if (email.length>0&&firstname.length>0&&lastname.length>0){
        let data={
            firstname:firstname,
            lastname:lastname,
            email:email
        }
        if (validateEmail(email)){
            document.getElementById("root").innerHTML="<button class=\"buttonload btn btn-success\">\n" +
                "  <i class=\"fa fa-refresh fa-spin\"></i>Loading\n" +
                "</button>";
            axios.post("/api/email",data)
                .then(function (response) {

                    let data=JSON.parse(JSON.stringify(response.data));
                    alert(data.message)
                    console.log(response.data)
                    if (data.success){
                        window.location.href="/"
                    }
                    else
                        location.reload()
                })
        } else alert("Email noto'g'ri!");
    }
    else {
        alert("Formalarni to'ldiring!")
        location.reload()
    }



}

function validateEmail(email)
{
    let re = /\S+@\S+\.\S+/;
    return re.test(email);
}


function getAll() {


    axios.get("/student/api/all")
        .then(function (response) {
            let out="<tr>\n" +
                "            <th>full name</th>\n" +
                "            <th>email</th>\n" +
                "        </tr>";
            let data=JSON.parse(JSON.stringify(response.data))
            console.log(data)
            for (let i = 0; i <data.length ; i++) {
                out+="<tr>\n" +
                    "            <td>"+data[i].fullName+"</td>\n" +
                    "            <td>"+data[i].email+"</td>\n" +
                    "        </tr>"
            }
            document.getElementById("table").innerHTML=out;
        })
}