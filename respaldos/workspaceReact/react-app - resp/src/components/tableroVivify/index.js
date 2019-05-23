import React, { Component } from 'react';
import common from './../vivifyData/common.json'
import data from './../vivifyData/data.json'
import organizations from './../vivifyData/organizations.json'
import $ from 'jquery'
import 'datatables.net'
import 'datatables.net-buttons'
import 'jszip'
import 'datatables.net-buttons/js/buttons.html5.js'
import 'datatables.net-buttons/js/buttons.colVis.js'
import 'datatables.net-buttons/js/buttons.flash.js'
import 'datatables.net-buttons/js/buttons.print.js'


export default class TableroVivify extends Component {
    constructor(props) {
        super(props)
        this.state = {
            tasks: data.tasks,
            data: data,
            common: common,
            organizations: organizations
        };
    }

    componentDidMount(){
        this.dataTableInitialize();
    }
    dataTableInitialize () {
        console.log('INITIALIZE DATABASE')
        $('#tableSearchColumn > thead  > tr')
          .clone(true)
          .appendTo('#tableSearchColumn > thead')
        $('#tableSearchColumn > thead > tr:eq(1) > th').each(function (i) {
          var title = $(this).text()
          $(this).html('<input type="text" placeholder="Search "' + title + '/>')
  
          $('input', this).on('keyup change', function () {
            if (table.column(i).search() !== this.value) {
              table
                .column(i)
                .search(this.value)
                .draw()
            }
          })
        })
  
       // $('#tableSearchColumn').DataTable().destroy()
        var table = $('#tableSearchColumn').DataTable({
          orderCellsTop: true,
          fixedHeader: true,
                      
            dom: 'Bfrtip',
            buttons: [
              'copy', 'csv', 'pdf', 'print'
            ],
          
        })
      }
    getBoardColumn(sprint_backlog_column_id) {
        for (let id in data.sprint_backlog_columns) {
            if (parseInt(data.sprint_backlog_columns[id].id) === parseInt(sprint_backlog_column_id)) {
                return data.sprint_backlog_columns[id].name
            }
        }
    }
    getSprintName(sprint_id) {
        for (let idx in data.sprints) {
            if (parseInt(data.sprints[idx].id) === parseInt(sprint_id)) {
                return data.sprints[idx].name
            }
        }
    }
    getBoardName(id) {
        for (let idx in organizations[0].boards) {

            if (parseInt(organizations[0].boards[idx].id) === parseInt(id)) {
                return organizations[0].boards[idx].name
            }
        }
    }
    getDoers(doers) {
        var output = "";
        for(let id in doers){
            for(let idx in data.board_users){
                if(doers[id].id === data.board_users[idx].user.id){
                    output += data.board_users[idx].user.first_name+" "+data.board_users[idx].user.last_name+", ";
                }
            }
        }
        output = (output.length > 2)?output.substring(0, output.length - 2): output;
        return output;        
    }
    taskInfo() {
        var rows = this.state.tasks.map((obj, i) => {
            let sgti = (obj.name.length > 0 && obj.name.charAt(0) !== "_")?obj.name.substring(0,5):"";

            return <tr key={obj.id}>
                <td>{i + 1}</td>
                <td>{sgti}</td>
                <td>{obj.name}</td>
                <td>{this.getBoardName(obj.board_id)}</td>
                <td>{obj.board_id}</td>
                <td>{JSON.stringify(obj.subtasks)}</td>
                <td>{this.getBoardColumn(obj.sprint_backlog_column_id)}</td>
                <td>{this.getSprintName(obj.sprint_id)}</td>
                <td>{this.getDoers(obj.doers)}</td>
            </tr>
        });
        return rows;

    }
    render() {
        // console.log(data.tasks);
        /* console.log(common);
         console.log(organizations);*/

        console.log(this.state)



        return (            
            <div className='table-responsive'>
                Vivify Report
                <table id="tableSearchColumn" className="dataTable table table-hover table-bordered">
                    <thead>
                        <tr>
                            <th>#</th>
                            <th>SGTI</th>
                            <th>Tarea</th>
                            <th>Board</th>
                            <th>Board Id</th>
                            <th>SubTasks</th>
                            <th>Status</th>
                            <th>Sprint ID</th>
                            <th>Doers</th>
                        </tr>
                    </thead>
                    <tbody>
                        {this.taskInfo()}
                    </tbody>
                </table>
            </div>
        );
    }
}

