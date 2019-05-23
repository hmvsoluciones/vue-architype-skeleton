import React, { Component } from 'react';
import data from './../../resources/data'

export default class Responsible extends Component {
    constructor(props) {
        super(props)
        this.state = {
            cards: data.cards,
            lists: data.lists,
            members: data.members
        }
    }
    getMembersNames(members) {
        var info= ""
        for(let id in members) {
            
            for(let idx in this.state.members){
                if(members[id] === this.state.members[idx].id){
                    info += this.state.members[idx].fullName + ",             "
                }
            }
        }
        return info
    }
    getCardInfo(idList){
            let members = ""

            for(let idx in this.state.cards){
                if(idList === this.state.cards[idx].idList && (this.state.cards[idx].name !== "" || this.state.cards[idx].name !== undefined) ){
                    members += this.state.cards[idx].name +": " +this.getMembersNames(this.state.cards[idx].idMembers).replace("<", "[").replace(">", "]") + "<hr/>"

                }
            }
            
        
        return members
    }
    render() {
        return (
            <div>
                Miembros asignados por tarea:
                <table border="1">
                    <thead>
                        <tr>
                            {this.state.lists.map(list =>
                                <th key={list.id}>{list.name}</th>
                            )}
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            {this.state.lists.map(list =>
                                <td key={list.id} valign="top">   
                                 <div dangerouslySetInnerHTML={{ __html: this.getCardInfo(list.id) }} />                                    
                                </td>
                            )}
                        </tr>
                    </tbody>
                </table>
            </div>
        );
    }
}

