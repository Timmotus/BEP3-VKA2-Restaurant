package nl.teamtwee.bep3.restaurant.payment.infrastructure.driver.web;

import java.util.UUID;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import nl.teamtwee.bep3.restaurant.payment.core.application.PaymentCommandHandler;
import nl.teamtwee.bep3.restaurant.payment.core.application.PaymentQueryHandler;
import nl.teamtwee.bep3.restaurant.payment.core.application.command.CreatePayment;
import nl.teamtwee.bep3.restaurant.payment.core.application.command.PayPayment;
import nl.teamtwee.bep3.restaurant.payment.core.application.query.GetAllPayments;
import nl.teamtwee.bep3.restaurant.payment.core.application.query.GetPaymentById;
import nl.teamtwee.bep3.restaurant.payment.core.domain.Payment;
import nl.teamtwee.bep3.restaurant.payment.infrastructure.driver.web.request.CreatePaymentRequest;
import nl.teamtwee.bep3.restaurant.payment.infrastructure.driver.web.response.PaymentResponse;

@RestController
@RequestMapping("/payment")
public class PaymentController {
    private final PaymentCommandHandler commandHandler;
    private final PaymentQueryHandler queryHandler;

    public PaymentController(PaymentCommandHandler commandHandler, PaymentQueryHandler queryHandler) {
        this.commandHandler = commandHandler;
        this.queryHandler = queryHandler;
    }

    @GetMapping
    public ResponseEntity<?> findAllPayments() {
        return ResponseEntity.ok(this.queryHandler.handle(new GetAllPayments()));
    }

    @GetMapping("{id}")
    public Payment findPaymentById(@PathVariable UUID id) {
        return this.queryHandler.handle(new GetPaymentById(id));
    }

    @PostMapping
    public ResponseEntity<?> registerPayment(@Valid @RequestBody CreatePaymentRequest request) {
        CreatePayment addPayment = new CreatePayment(
                request.orderId,
                request.amount);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new PaymentResponse(this.commandHandler.handle(addPayment)));
    }

    @PostMapping("/{id}/pay")
    public ResponseEntity<?> payPayment(@PathVariable UUID id) {
        return ResponseEntity.ok(this.commandHandler.handle(new PayPayment(id)));
    }

    // @GetMapping("/orderIdPayment/{id}")
    // public Payment findPaymentByOrderId(@PathVariable UUID id) {
    // return this.queryHandler.handle(new GetPaymentByOrderId(id));
    // }
}
