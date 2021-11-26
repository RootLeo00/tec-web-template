    'use strict';

    class App extends React.Component {
    constructor(){
            super();
            this.state = {
            expression:"",
            result: ""
            }
            this.onClick = this.onClick.bind(this); 
    }

    onClick(e) {
            let button = e.target.name
            if(button === "="){

                this.calculate()
            }

        else if(button === "C"){
            this.reset()
        }
        else if(button === "CE"){
            this.backspace()
        }
        else if(button === "ln"||button === "sqrt"||button === "exp"||button === "1/x"){
            this.scientificCalculator(button)
        }
        else {
            this.setState({
                expression: this.state.expression + button,
                result: this.state.result
                
            })
        }
    };

//NB: la consegna dice che l'argomento dei tasti "scientifici" è l'espressione mostrata nel display al momento della invocazione
    scientificCalculator(op) {
            try {
                const data={
                        x:eval(this.state.expression),
                        op:op
                    };
                    //metodo requestCalculate
                    //requestCalculate( "./calculateServlet",(data),//chiama la servlet (./ parte dalla cartella attuale in cui si trova)
                       // (res) => {
                          //    this.setState({result: res, expression: op+"("+this.state.expression+")"})
                          //    }
                       // );

                    //metodo fetch con POST
                    //NB stringify is done in the fetch method
                postData(//chiama il js del calcolo passando l'uri della servlet e il dato
                    "./calculateServlet",(data),//chiama la servlet (./ parte dalla cartella attuale in cui si trova)
                (res) => {
                this.setState({result: res, expression: op+"("+this.state.expression+")"})
                }
                        );


            } catch (e) {
                this.setState({
            result: "error"
                })

            }
    };

      calculate() {
        try {
              this.setState({
              result: (eval(this.state.expression) || "" ) + ""
            })
        } catch (e) {
            this.setState({
          result: "error"
            })

        }
  };


    reset(){
        this.setState({
            expression:"",
            result: ""
        })
            };


    backspace(){
        this.setState({
            expression: this.state.expression.slice(0, -1),
        })
    };

    render() {
        return (

            <div className="calculator-body">
                <h1>Calcolatrice</h1>
                <Display result={this.state.expression}/>
                <Display result={this.state.result}/>
                <KeyBoard onClick={this.onClick}/>
            </div>

        );
    }
    }
