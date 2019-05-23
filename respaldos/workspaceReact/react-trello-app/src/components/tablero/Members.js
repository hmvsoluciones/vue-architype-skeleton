import React, { Component } from 'react';
import data from './../../resources/data'

export default class Members extends Component {
    constructor(props) {
        super(props)
        this.state = {
            cards: data.cards,
            lists: data.lists,
            members: data.members
        }
    }
    getMembersNames(members) {
        var info = ""
        for (let id in members) {

            for (let idx in this.state.members) {
                if (members[id] === this.state.members[idx].id) {
                    info += this.state.members[idx].fullName + ",             "
                }
            }
        }
        return info
    }
    getCardInfo(idList) {
        let members = ""

        for (let idx in this.state.cards) {
            if (idList === this.state.cards[idx].idList && (this.state.cards[idx].name !== "" || this.state.cards[idx].name !== undefined)) {
                members += this.state.cards[idx].name + ": " + this.getMembersNames(this.state.cards[idx].idMembers)

            }
        }


        return members
    }
    getTaskByMemberId(id) {

        let tasks = ""
        for (let idx in this.state.cards) {
            if (this.state.cards[idx].idMembers.includes(id)) {
                tasks += this.state.cards[idx].name + ",    "
            }

        }
        return tasks
    }
    render() {
        return (
            <div>
                Tareas por miembro
                <table border="1">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Tareas</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.state.members.map(member =>
                            <tr key={member.id}>
                                <td>{member.fullName}</td>
                                <td>{this.getTaskByMemberId(member.id)}</td>
                            </tr>
                        )}
                    </tbody>
                </table>
            </div>
        );
    }
}

