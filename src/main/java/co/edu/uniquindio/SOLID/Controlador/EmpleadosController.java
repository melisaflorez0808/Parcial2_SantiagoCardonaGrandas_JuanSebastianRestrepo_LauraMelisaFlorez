package co.edu.uniquindio.SOLID.Controlador;

import co.edu.uniquindio.SOLID.Model.DTO.ClienteDTO;
import co.edu.uniquindio.SOLID.Model.DTO.EmpleadoDTO;
import co.edu.uniquindio.SOLID.Model.Empleado;
import co.edu.uniquindio.SOLID.Model.Minimercado;
import co.edu.uniquindio.SOLID.Service.Fachadas.EmpleadoFacade;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class EmpleadosController implements Initializable {

    @FXML private TextField txtEmpId;
    @FXML private TextField txtEmpNombre;
    @FXML private ComboBox<Empleado.Rol> cmbEmpRol;
    @FXML private TableView<EmpleadoDTO> tblEmpleados;
    @FXML private TableColumn<EmpleadoDTO, String> colEmpId;
    @FXML private TableColumn<EmpleadoDTO, String> colEmpNombre;
    @FXML private TableColumn<EmpleadoDTO, String> colEmpRol;
    @FXML private TableColumn<EmpleadoDTO, String> colEmpEstado;

    private ObservableList<EmpleadoDTO> empleados;
    private EmpleadoFacade empleadoFacade;
    private EmpleadoDTO empleadoSeleccionado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        empleadoFacade = new EmpleadoFacade();
        empleados = FXCollections.observableArrayList();
        cmbEmpRol.setItems(FXCollections.observableArrayList(Empleado.Rol.values()));
        configurarTabla();
        cargarEmpleados();
        configurarSeleccionTabla();
    }

    private void configurarSeleccionTabla() {
        tblEmpleados.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                empleadoSeleccionado = newSelection;
                cargarDatosEnFormulario(newSelection);
            }
        });
    }

    private void cargarDatosEnFormulario(EmpleadoDTO empleadoDTO) {
        txtEmpId.setDisable(true);
        txtEmpId.setText(empleadoDTO.getId());
        txtEmpNombre.setText(empleadoDTO.getNombre());
        cmbEmpRol.setValue(empleadoDTO.getRol());
    }

    public void configurarTabla(){
        colEmpId.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getId()));
        colEmpNombre.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getNombre()));
        colEmpRol.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().getRol().name()));
        colEmpEstado.setCellValueFactory(cd -> new javafx.beans.property.SimpleStringProperty(cd.getValue().isActivo() ? "Activo" : "Inactivo"));
        tblEmpleados.setItems(empleados);
    }

    private void cargarEmpleados() {
        empleados.clear();
        empleados.addAll(empleadoFacade.obtenerTodosLosEmpleados());
        mostrarMensaje("Clientes cargados: " + empleados.size());
    }

    private void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }

    public void configurarCamposRol() {
        if (cmbEmpRol != null) {
            cmbEmpRol.setItems(FXCollections.observableArrayList(Empleado.Rol.values()));
            cmbEmpRol.setValue(Empleado.Rol.CAJERO);
        }
    }

    @FXML
    void crearEmpleado(ActionEvent event) {
        try {
            if (!validarCampos()) {
                return;
            }

            EmpleadoDTO emp = new EmpleadoDTO(
                    txtEmpId.getText().trim(),
                    txtEmpNombre.getText().trim(),
                    cmbEmpRol.getValue());

            if (empleadoFacade.agregarEmpleado(emp)) {
                cargarEmpleados();
                mostrarMensaje("Empleado agregado exitosamente");
                System.out.println("Empleado agregado: " + emp.getId());
            } else {
                mostrarMensaje("Error: Ya existe un empleado con la cédula " + emp.getId());
            }
        } catch (Exception e) {
            mostrarMensaje("Error al agregar empleado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private boolean validarCampos() {
        if (txtEmpId.getText().trim().isEmpty()) {
            mostrarMensaje("El Id es obligatorio");
            return false;
        }

        if (txtEmpNombre.getText().trim().isEmpty()) {
            mostrarMensaje("El nombre es obligatorio");
            return false;
        }

        if (cmbEmpRol.getValue() == null) {
            mostrarMensaje("El rol es obligatorio");
            return false;
        }
        return true;
    }

    @FXML
    void actualizarEmpleado() {
        try {
            if (empleadoSeleccionado == null) {
                mostrarMensaje("Seleccione un empleado de la tabla para actualizar");
                return;
            }
            if (!validarCampos()) {
                return;
            }
            empleadoSeleccionado.setNombre(txtEmpNombre.getText().trim());
            empleadoSeleccionado.setRol(cmbEmpRol.getValue());

            if (empleadoFacade.actualizarEmpleado(empleadoSeleccionado)) {
                tblEmpleados.refresh(); //es como decir actualizar
                mostrarMensaje("Empleado actualizado exitosamente");
                System.out.println("Empleado actualizado: " + empleadoSeleccionado.getId());
            } else {
                mostrarMensaje("Error: No se pudo actualizar el empleado");
            }
        } catch (Exception e) {
            mostrarMensaje("Error al actualizar el empleado: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void eliminarEmpleado() {
        try {
            if (empleadoSeleccionado == null) {
                mostrarMensaje("Seleccione un empleado de la tabla para eliminar");
                return;
            }
            Alert confirmacion = new Alert(Alert.AlertType.CONFIRMATION);
            confirmacion.setTitle("Confirmar Eliminación");
            confirmacion.setHeaderText("¿Está seguro de eliminar este cliente?");
            confirmacion.setContentText("Cliente: " + empleadoSeleccionado.getNombre() +
                    "\nCédula: " + empleadoSeleccionado.getId());

            Optional<ButtonType> resultado = confirmacion.showAndWait();

            if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
                if (empleadoFacade.eliminarEmpleado(empleadoSeleccionado.getId())) {
                    cargarEmpleados();
                    mostrarMensaje("Empleado eliminado exitosamente");
                    System.out.println("Empleado eliminado: " + empleadoSeleccionado.getId());
                } else {
                    mostrarMensaje("Error: No se pudo eliminar el empleado");
                }
            }
        } catch (Exception e) {
            mostrarMensaje("Error al eliminar cliente: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    void activarEmpleado() {
        String id = txtEmpId != null ? txtEmpId.getText() : null;
        if (id == null || id.trim().isEmpty()) { mostrarMensaje("El ID es obligatorio"); }
        try {
            EmpleadoDTO empleadoDTO = empleadoFacade.buscarEmpleadoPorCedula(id);
            empleadoFacade.activarEmpleado(empleadoDTO);
            tblEmpleados.refresh();
        } catch (IllegalArgumentException e) { mostrarMensaje(e.getMessage()); }
    }

    @FXML
    void inactivarEmpleado() {
        String id = txtEmpId != null ? txtEmpId.getText() : null;
        if (id == null || id.trim().isEmpty()) { mostrarMensaje("El ID es obligatorio"); }
        try {
            EmpleadoDTO empleadoDTO = empleadoFacade.buscarEmpleadoPorCedula(id);
            empleadoFacade.inactivarEmpleado(empleadoDTO);
            tblEmpleados.refresh();
        } catch (IllegalArgumentException e) { mostrarMensaje(e.getMessage()); }
    }
}


