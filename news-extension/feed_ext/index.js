const results = document.querySelector(".result");
const api = "http://localhost:8080/feeds?tag=";
const search = document.getElementById("form-data");


  const getposts = async () => {
    try {
     const tag = await document.getElementById("searchTxt").value;
      const response = await axios.get(`${api}${tag}`);
      console.log("res:"+response);
      console.log("res:"+response.data[0].title);
    console.log("res:"+response.data[0].link);
    console.log("res:"+response.data[0].description);
    console.log("res:"+response.data[0].pubDate);
    console.log("res:"+response.data[0].publisher);
    console.log("res:"+response.data.length);

var articles="";
        if(response.data.length!=0){
            for(var i=0;i<response.data.length;i++){
            articles+='<li class="home"><a href='+response.data[i].link+' target="_blank">'+response.data[i].title+'</a>';
            articles+='<br><center>'+response.data[i].description;
            articles+='<br><b><center>'+response.data[i].pubDate;
            articles+='<br><center>'+response.data[i].publisher+'</b>';
            articles+='</li>';
}
}
     results.innerHTML=articles;
  }
      catch (error) {
      console.log("error")
 results.innerHTML="";
    }
  };
 
  // declare a function to handle form submission
  const handleSubmit = async e => {
    e.preventDefault();
    getposts();

  };
 
  search.addEventListener("submit", e => handleSubmit(e));