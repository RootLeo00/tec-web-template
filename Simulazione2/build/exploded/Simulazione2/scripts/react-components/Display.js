'use strict';

class Display extends React.Component {

    render() {
    let result = this.props.result;
    console.log(result);
    if(result=="error"){
        return( <div className="result">
        <p><strong>Error</strong></p>
        </div>
        );
    }
    else{
    return (
        <div className="result">
        <p><strong>{result.name}</strong><br></br>
        {result.description}<br></br>
        </p>
        </div>
        );
    }
}
}
