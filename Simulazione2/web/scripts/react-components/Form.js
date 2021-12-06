'use strict';

class Form extends React.Component {

    render() {
    return (
        <div className="position">
            <form name="sub" onSubmit={this.props.onSubmit}>
                <label> Coordinate x: </label><input type="text" name="x" onChange={this.props.onChange}></input><br></br>
             <label> Coordinate y: </label><input type="text" name="y" onChange={this.props.onChange}></input><br></br>
            <input type="submit" name="submit" value="SUBMIT"></input><br/>
            </form>
        </div>
        );
    }
}
