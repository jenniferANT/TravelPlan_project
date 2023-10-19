import Header from "./Header";
import Content from "./Content";

function DefaultLayout( {children }) {
    return (
        <div>
            <Header />
            <div className="Container">
                <Content>{children}</Content>
            </div>
        </div>
    )
}

export default DefaultLayout