import React from "react";
import { NavLink } from "react-router-dom";

const Header = () => {

    return (
        <header>
            <h1>Challenges</h1>
            <nav>
                <NavLink to="/" > Home</NavLink>
                <NavLink to="/challenge-1" >Challenge 1</NavLink>
                <NavLink to="/challenge-2" >Challenge 2</NavLink>
                <NavLink to="/challenge-3">Challenge 3</NavLink>
            </nav>
        </header>
    );
}

export default Header;