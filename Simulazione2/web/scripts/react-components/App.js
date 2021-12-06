'use strict';

class App extends React.Component {
  constructor(){
        super();
        this.state = {
        x:"",
        y: "",
        result: ""
        }
        this.onSubmit = this.onSubmit.bind(this); 
        this.onChange = this.onChange.bind(this); 
        
  }

  onSubmit(e) {
        let submit = e.target.name
        if(submit == "sub"){
            if(this.state.x ==undefined || this.state.y==undefined ||this.state.x =="" || this.state.y=="" ){
                this.setState({
              result:error
          })
            }
            else{
          this.setState({
              x: this.state.x,
              y: this.state.y 
          })
        this.showInfo();
        }
      }
  };


  showInfo() {
            try {
                const data={
                        x:this.state.x,
                        y:this.state.y
                    };
                    
                    //fetch POST method
                    //NB stringify is done in the fetch method
             // postData("./infoservlet",(data),(res) => {this.setState({result: res})  });

                    //request XML method
                    //NB stringify is done in the requestServer method
                    requestServer("./infoservlet",(data),(res) => {this.setState({result: JSON.parse(res)})  });
                                        
            } catch (e) {
                this.setState({
            result: "error"
                })

            }
  };


onChange(e) {
    if(e.target.name=="x"){
    this.setState({
      x: e.target.value
    });
    }
    if(e.target.name=="y"){
            this.setState({
      y: e.target.value
    });
    }

  }


  render() {
      return (

        <div className="app-body">
            <h1>Tourist position info</h1>
            <Form onSubmit={this.onSubmit} onChange={this.onChange}/>
            <Display result={this.state.result}/>
        </div>

      );
  }
}
